package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import uk.ac.sheffield.jast.XMLReader;
import uk.ac.sheffield.jast.XMLWriter;
import uk.ac.sheffield.jast.xml.Content;
import uk.ac.sheffield.jast.xml.Document;
import uk.ac.sheffield.jast.xml.Element;
import uk.ac.sheffield.jast.xml.Instruction;
import uk.ac.sheffield.jast.xpath.XPath;

public class ManageTeam {
    static Scanner scan;

    static {
        scan = new Scanner(System.in);
    }

    public ManageTeam() {
    }

    private static void write(String text) {
        System.out.println(text);
    }

    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException, IOException {
        Throwable var2 = null;
        String action = null;

        String postData;
        String email1;
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            try {
                email1 = reader.readLine();
                postData = URLDecoder.decode(email1, "UTF-8");
            } finally {
                if (reader != null) {
                    reader.close();
                }

            }
        } catch (Throwable var130) {
            if (var2 == null) {
                var2 = var130;
            } else if (var2 != var130) {
                var2.addSuppressed(var130);
            }

            throw var2;
        }

        Map<String, String> webForm = new LinkedHashMap();
        String[] var6;
        int var138 = (var6 = postData.split("&")).length;

        for(int var136 = 0; var136 < var138; ++var136) {
            action = var6[var136];
            int pos = action.indexOf("=");
            String key = action.substring(0, pos);
            String val = action.substring(pos + 1);
            webForm.put(key, val);
        }

        action = (String)webForm.get("action");
        String identity = (String)webForm.get("identity");
        email1 = (String)webForm.get("email1");
        String email2 = (String)webForm.get("email2");
        File file1 = new File("../campus_only/xml/allocatedTeams.xml");
        File file2 = new File("../campus_only/xml/allocatedTeams_team.xml");
        File file3 = new File("../campus_only/xml/allocatedTeams_assessor.xml");
        Throwable var10 = null;
        Object var11 = null;

        XMLReader reader;
        Document document;
        Element RootElement;
        try {
            reader = new XMLReader(file1);

            try {
                document = reader.readDocument();
                RootElement = document.getRootElement();
                List<Element> drop = RootElement.getChildren("Drop");
                Iterator var17;
                if ("drop".equals(action)) {
                    var17 = drop.iterator();

                    while(var17.hasNext()) {
                        Element Drop = (Element)var17.next();
                        XPath findStu = new XPath("Teams/Team/Student/Email='" + email2 + "'/..");
                        List<Content> resultStu = findStu.match(document);
                        if (resultStu.isEmpty()) {
                            write("Content-Type: text/html");
                            write("");
                            write("<!DOCTYPE html>");
                            write("<html><head>");
                            write("<title>Error</title>");
                            write("</head><body>");
                            write("<h2>Wrong Email!</h2><p>");
                            write("The email you input doesn't exist!");
                            write("</p></body></html>");
                        }

                        for(int i = 0; i < resultStu.size(); ++i) {
                            Element Student = (Element)resultStu.get(i);
                            Element Team = (Element)Student.getParent();
                            write("Content-Type: text/html");
                            write("");
                            write("<!DOCTYPE html>");
                            write("<html><head>");
                            write("<title>Result</title>");
                            write("</head><body>");
                            write("<h2>Drop out success!</h2><p>");
                            write("The student <strong>" + Student.getChild("Forename").getText() + " " + Student.getChild("Surname").getText() + "</strong> was in: " + Team.getValue("identity"));
                            write("</p><p>");
                            Content toMove = Student.detach();
                            Drop.addContent(toMove);
                            write("Now the student is dropped out.");
                            write("</p></body></html>");
                        }
                    }
                } else {
                    XPath findNewTeam = new XPath("Teams/Team[@identity='" + identity + "']");
                    List<Content> resultNewTeam = findNewTeam.match(document);
                    if (resultNewTeam.isEmpty()) {
                        write("Content-Type: text/html");
                        write("");
                        write("<!DOCTYPE html>");
                        write("<html><head>");
                        write("<title>Error</title>");
                        write("</head><body>");
                        write("<h2>Wrong Team!</h2><p>");
                        write("The team you input doesn't exist!");
                        write("</p></body></html>");
                    }

                    for(int i = 0; i < resultNewTeam.size(); ++i) {
                        Element Team = (Element)resultNewTeam.get(i);
                        XPath findStu = new XPath("Teams/Team/Student/Email='" + email1 + "'/..");
                        List<Content> resultStu = findStu.match(document);
                        if (resultStu.isEmpty()) {
                            write("Content-Type: text/html");
                            write("");
                            write("<!DOCTYPE html>");
                            write("<html><head>");
                            write("<title>Error</title>");
                            write("</head><body>");
                            write("<h2>Wrong Email!</h2><p>");
                            write("The email you input doesn't exist!");
                            write("</p></body></html>");
                        }

                        for(int j = 0; j < resultStu.size(); ++j) {
                            Element Student = (Element)resultStu.get(j);
                            Content toMove = Student.detach();
                            Team.addContent(toMove);
                            write("Content-Type: text/html");
                            write("");
                            write("<!DOCTYPE html>");
                            write("<html><head>");
                            write("<title>Result</title>");
                            write("</head><body>");
                            write("<h2>Change team success!</h2><p>");
                            write("The student <strong>" + Student.getChild("Forename").getText() + " " + Student.getChild("Surname").getText() + "</strong> is moved into new team: <strong>" + identity + "</strong>.");
                            write("</p></body></html>");
                        }
                    }
                }

                Throwable var145 = null;
                var17 = null;

                try {
                    XMLWriter writer = new XMLWriter(file1);

                    try {
                        writer.writeDocument(document);
                        writer.close();
                    } finally {
                        if (writer != null) {
                            writer.close();
                        }

                    }
                } catch (Throwable var132) {
                    if (var145 == null) {
                        var145 = var132;
                    } else if (var145 != var132) {
                        var145.addSuppressed(var132);
                    }

                    throw var145;
                }
            } finally {
                if (reader != null) {
                    reader.close();
                }

            }
        } catch (Throwable var134) {
            if (var10 == null) {
                var10 = var134;
            } else if (var10 != var134) {
                var10.addSuppressed(var134);
            }

            throw var10;
        }

        var10 = null;
        var11 = null;

        Content toMove;
        XMLWriter writer;
        try {
            reader = new XMLReader(file1);

            try {
                document = reader.readDocument();
                RootElement = document.getRootElement();
                toMove = RootElement.detach();
                document.addContent((new Instruction("xml-stylesheet")).setValue("href", "teamView.xsl").setValue("type", "text/xsl"));
                document.addContent(toMove);
                writer = new XMLWriter(file2);
                writer.writeDocument(document);
                writer.close();
            } finally {
                if (reader != null) {
                    reader.close();
                }

            }
        } catch (Throwable var128) {
            if (var10 == null) {
                var10 = var128;
            } else if (var10 != var128) {
                var10.addSuppressed(var128);
            }

            throw var10;
        }

        var10 = null;
        var11 = null;

        try {
            reader = new XMLReader(file1);

            try {
                document = reader.readDocument();
                RootElement = document.getRootElement();
                toMove = RootElement.detach();
                document.addContent((new Instruction("xml-stylesheet")).setValue("href", "teamView.xsl").setValue("type", "text/xsl"));
                document.addContent(toMove);
                writer = new XMLWriter(file3);
                writer.writeDocument(document);
                writer.close();
            } finally {
                if (reader != null) {
                    reader.close();
                }

            }

        } catch (Throwable var126) {
            if (var10 == null) {
                var10 = var126;
            } else if (var10 != var126) {
                var10.addSuppressed(var126);
            }

            throw var10;
        }
    }
}
