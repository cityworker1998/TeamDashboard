package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLDecoder;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Scanner;
import uk.ac.sheffield.jast.XMLReader;
import uk.ac.sheffield.jast.XMLWriter;
import uk.ac.sheffield.jast.xml.Content;
import uk.ac.sheffield.jast.xml.Document;
import uk.ac.sheffield.jast.xml.Element;
import uk.ac.sheffield.jast.xpath.XPath;

public class SubmitPair {
    public SubmitPair() {
    }

    public static void main(String[] args) throws IOException {
        new Scanner(System.in);
        Throwable var3 = null;
        LinkedHashMap webForm = null;

        String postData;
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            try {
                String encoded = reader.readLine();
                postData = URLDecoder.decode(encoded, "UTF-8");
            } finally {
                if (reader != null) {
                    reader.close();
                }

            }
        } catch (Throwable var35) {
            if (var3 == null) {
                var3 = var35;
            } else if (var3 != var35) {
                var3.addSuppressed(var35);
            }

            throw var3;
        }

        String checkData = postData.toLowerCase();
        if (checkData.contains("script") || checkData.contains(".js")) {
            logError("Malicious script injection detected!");
            logError("Do not attempt to hack this website!");
            sendResponse();
            System.exit(1);
        }

        webForm = new LinkedHashMap();
        String[] var8;
        int var7 = (var8 = postData.split("&")).length;

        String input1;
        String input2;
        for(int var41 = 0; var41 < var7; ++var41) {
            String entry = var8[var41];
            int pos = entry.indexOf("=");
            input1 = entry.substring(0, pos);
            input2 = entry.substring(pos + 1);
            webForm.put(input1, input2);
        }

        File file_teams = new File("../campus_only/xml/example_Teams.xml");
        Throwable var42 = null;
        Element root = null;

        try {
            XMLReader reader = new XMLReader(file_teams);

            try {
                Document document_teams = reader.readDocument();
                reader.close();
                input1 = (String)webForm.get("email1");
                input2 = (String)webForm.get("email2");
                XPath findExtra1 = new XPath("/Teams/Team/Student[Email='" + input1 + "']/Email");
                List<Content> resultExtra1 = findExtra1.match(document_teams);
                if (!resultExtra1.isEmpty()) {
                    for(int i = 0; i < resultExtra1.size(); ++i) {
                        Element extra = (Element)resultExtra1.get(i);
                        write("Content-Type: text/html");
                        write("");
                        write("<!DOCTYPE html>");
                        write("<html><head>");
                        write("<title>Error</title>");
                        write("</head><body>");
                        write("<h2>Information illegal!</h2><p>");
                        write("You have submitted a existing email which is : " + extra.getText());
                        write("</p></body></html>");
                    }

                    System.exit(1);
                }

                XPath findExtra2 = new XPath("/Teams/Team/Student[Email='" + input2 + "']/Email");
                List<Content> resultExtra2 = findExtra2.match(document_teams);
                if (!resultExtra2.isEmpty()) {
                    for(int i = 0; i < resultExtra2.size(); ++i) {
                        Element extra = (Element)resultExtra2.get(i);
                        write("Content-Type: text/html");
                        write("");
                        write("<!DOCTYPE html>");
                        write("<html><head>");
                        write("<title>Error</title>");
                        write("</head><body>");
                        write("<h2>Information illegal!</h2><p>");
                        write("You have submitted a existing email which is : " + extra.getText());
                        write("</p></body></html>");
                    }

                    System.exit(1);
                }
            } finally {
                if (reader != null) {
                    reader.close();
                }

            }
        } catch (Throwable var37) {
            if (var42 == null) {
                var42 = var37;
            } else if (var42 != var37) {
                var42.addSuppressed(var37);
            }

            throw var42;
        }

        Document document_pair = new Document();
        root = (new Element("Pair")).setValue("cohort", (String)webForm.get("level1"));
        root.addContent((new Element("Student")).addContent((new Element("Forename")).setText((String)webForm.get("forename1"))).addContent((new Element("Surname")).setText((String)webForm.get("surname1"))).addContent((new Element("Email")).setText((String)webForm.get("email1"))).addContent((new Element("Region")).setText((String)webForm.get("region1"))).addContent((new Element("Degree")).setText((String)webForm.get("degree1"))).addContent((new Element("Level")).setText((String)webForm.get("level1"))));
        root.addContent((new Element("Student")).addContent((new Element("Forename")).setText((String)webForm.get("forename2"))).addContent((new Element("Surname")).setText((String)webForm.get("surname2"))).addContent((new Element("Email")).setText((String)webForm.get("email2"))).addContent((new Element("Region")).setText((String)webForm.get("region2"))).addContent((new Element("Degree")).setText((String)webForm.get("degree2"))).addContent((new Element("Level")).setText((String)webForm.get("level2"))));
        document_pair.setRootElement(root);
        String id1 = ((String)webForm.get("email1")).toLowerCase().substring(0, ((String)webForm.get("email1")).lastIndexOf("@"));
        String id2 = ((String)webForm.get("email2")).toLowerCase().substring(0, ((String)webForm.get("email2")).lastIndexOf("@"));
        File file_pair = new File("../campus_only/xml/pair/pair_" + id1 + "&" + id2 + ".xml");
        XMLWriter writer = new XMLWriter(file_pair);
        writer.writeDocument(document_pair);
        write("Content-Type: text/html");
        write("");
        write("<!DOCTYPE html>");
        write("<html><head>");
        write("<title>Successful submit</title>");
        write("</head><body>");
        write("<h2>You have submit successfully.</h2>");
        write("<p>File is created to store your pair.</p>");
        write("</body></html>");
        writer.close();
    }

    private static void logError(String string) {
        System.out.println(string);
    }

    private static void write(String text) {
        System.out.println(text);
    }

    public static void sendResponse() {
        write("Content-Type: text/html");
        write("");
        write("<!DOCTYPE html>");
        write("<html><head>");
        write("<title>Bad</title>");
        write("</head><body>");
        write("<h1>Something wrong!</h1>");
        write("</body></html>");
    }
}