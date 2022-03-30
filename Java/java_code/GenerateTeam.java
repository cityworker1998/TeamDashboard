package test;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.URLDecoder;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import uk.ac.sheffield.jast.XMLReader;
import uk.ac.sheffield.jast.XMLWriter;
import uk.ac.sheffield.jast.xml.Document;
import uk.ac.sheffield.jast.xml.Element;

public class GenerateTeam {
    public GenerateTeam() {
    }

    private static void write(String text) {
        System.out.println(text);
    }

    public static int toInt(String string) {
        int parseInt = Integer.valueOf(string);
        return parseInt;
    }

    public static int getInt(int b, int c) {
        return b * 2 % c == 0 ? b * 2 / c : b * 2 / c + 1;
    }

    public static void main(String[] args) throws IOException {
        Throwable var2 = null;
        String cohort = null;

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
        } catch (Throwable var51) {
            if (var2 == null) {
                var2 = var51;
            } else if (var2 != var51) {
                var2.addSuppressed(var51);
            }

            throw var2;
        }

        Map<String, String> webForm = new LinkedHashMap();
        String[] var6;
        int size = (var6 = postData.split("&")).length;

        for(int var55 = 0; var55 < size; ++var55) {
            cohort = var6[var55];
            int pos = cohort.indexOf("=");
            String key = cohort.substring(0, pos);
            String val = cohort.substring(pos + 1);
            webForm.put(key, val);
        }

        cohort = (String)webForm.get("cohort");
        String diversity = (String)webForm.get("diversity");
        size = toInt((String)webForm.get("size"));
        File folder = new File("../campus_only/xml/pair/");
        File[] listOfFiles = folder.listFiles();
        int fileNumber = listOfFiles.length;
        new ByteArrayOutputStream();
        new ByteArrayOutputStream();
        new ByteArrayOutputStream();
        new ByteArrayOutputStream();
        new ByteArrayOutputStream();
        new ByteArrayOutputStream();
        int A = 0;
        String[] forename = new String[fileNumber * 2];
        String[] surname = new String[fileNumber * 2];
        String[] email = new String[fileNumber * 2];
        String[] region = new String[fileNumber * 2];
        String[] degree = new String[fileNumber * 2];
        String[] level = new String[fileNumber * 2];
        File[] var25 = listOfFiles;
        int var24 = listOfFiles.length;

        for(int var23 = 0; var23 < var24; ++var23) {
            File file_pair = var25[var23];
            Throwable var26 = null;
            Object var27 = null;

            try {
                XMLReader reader = new XMLReader(file_pair);

                try {
                    Document document_pair = reader.readDocument();
                    Element pair = document_pair.getRootElement();
                    List<Element> student = pair.getChildren("Student");

                    for(Iterator var33 = student.iterator(); var33.hasNext(); ++A) {
                        Element Student = (Element)var33.next();
                        ByteArrayOutputStream forenameContent = consoleContent(Student.getChild("Forename").getText());
                        ByteArrayOutputStream surnameContent = consoleContent(Student.getChild("Surname").getText());
                        ByteArrayOutputStream emailContent = consoleContent(Student.getChild("Email").getText());
                        ByteArrayOutputStream regionContent = consoleContent(Student.getChild("Region").getText());
                        ByteArrayOutputStream degreeContent = consoleContent(Student.getChild("Degree").getText());
                        ByteArrayOutputStream levelContent = consoleContent(Student.getChild("Level").getText());
                        forename[A] = forenameContent.toString().replaceAll("[\\\r\\\n]+", "");
                        surname[A] = surnameContent.toString().replaceAll("[\\\r\\\n]+", "");
                        email[A] = emailContent.toString().replaceAll("[\\\r\\\n]+", "");
                        region[A] = regionContent.toString().replaceAll("[\\\r\\\n]+", "");
                        degree[A] = degreeContent.toString().replaceAll("[\\\r\\\n]+", "");
                        level[A] = levelContent.toString().replaceAll("[\\\r\\\n]+", "");
                    }
                } finally {
                    if (reader != null) {
                        reader.close();
                    }

                }
            } catch (Throwable var53) {
                if (var26 == null) {
                    var26 = var53;
                } else if (var26 != var53) {
                    var26.addSuppressed(var53);
                }

                throw var26;
            }
        }

        int teamNumber = getInt(fileNumber, size);
        Document document_team = new Document();
        Element root = new Element("Teams");

        int j;
        for(j = 0; j < teamNumber; ++j) {
            root.addContent((new Element("Team")).setValue("identity", "Team" + String.valueOf(j + 1)));
        }

        for(j = 0; j < teamNumber; ++j) {
            List<Element> team = root.getChildren("Team");

            for(int i = 0; i < size && i + j * size != A; ++i) {
                Element Team = (Element)team.get(j);
                Team.addContent((new Element("Student")).setValue("id", "stu" + String.valueOf(i + j * size + 1)).addContent((new Element("Forename")).setText(forename[i + j * size])).addContent((new Element("Surname")).setText(surname[i + j * size])).addContent((new Element("Email")).setText(email[i + j * size])).addContent((new Element("Region")).setText(region[i + j * size])).addContent((new Element("Degree")).setText(degree[i + j * size])).addContent((new Element("Level")).setText(level[i + j * size])));
            }
        }

        root.addContent((new Element("Drop")).setValue("identity", "Dropped_Students").addContent((new Element("Student")).setValue("id", "drop_id").addContent((new Element("Forename")).setText("drop_forename")).addContent((new Element("Surname")).setText("drop_surname")).addContent((new Element("Email")).setText("drop_email")).addContent((new Element("Region")).setText("drop_region")).addContent((new Element("Degree")).setText("drop_degree")).addContent((new Element("Level")).setText("drop_level"))));
        document_team.setRootElement(root);
        File file = new File("../campus_only/xml/Teams.xml");
        file.createNewFile();
        write("Content-Type: text/html");
        write("");
        write("<!DOCTYPE hmtl>");
        write("<html><head>");
        write("<title>Successful generate</title>");
        write("</head><body>");
        write("<h2>Generate successfully.</h2>");
        write("<p>File is created to store generated teams.</p>");
        write("</body></html>");
        XMLWriter writer = new XMLWriter(file);
        writer.writeDocument(document_team);
        writer.close();
    }

    private static ByteArrayOutputStream consoleContent(String string) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        PrintStream old = System.out;
        System.setOut(ps);
        System.out.println(string);
        System.out.flush();
        System.setOut(old);
        return baos;
    }
}
