package test;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import uk.ac.sheffield.jast.XMLReader;
import uk.ac.sheffield.jast.XMLWriter;
import uk.ac.sheffield.jast.xml.Content;
import uk.ac.sheffield.jast.xml.Document;
import uk.ac.sheffield.jast.xml.Element;
import uk.ac.sheffield.jast.xml.Instruction;
import uk.ac.sheffield.jast.xpath.XPath;

public class AllocateAssessor {
    public AllocateAssessor() {
    }

    private static void write(String text) {
        System.out.println(text);
    }

    public static int toInt(String string) {
        int parseInt = Integer.valueOf(string);
        return parseInt;
    }

    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException, IOException {
        File file = new File("../campus_only/xml/Teams.xml");
        Throwable var2 = null;
        Throwable var3 = null;

        XMLReader reader;
        Document document;
        try {
            reader = new XMLReader(file);

            try {
                document = reader.readDocument();
                reader.close();
                XPath TeamNumber = new XPath("Teams/Team");
                List<Content> teamNumber = TeamNumber.match(document);
                XPath TeamSize = new XPath("Teams/Team[@identity='Team1']/Student");
                List<Content> teamSize = TeamSize.match(document);
                Element Teams = document.getRootElement();
                List<Element> team = Teams.getChildren("Team");
                int j = 0;

                while(true) {
                    if (j >= teamNumber.size()) {
                        File file_new = new File("../campus_only/xml/allocatedTeams.xml");
                        XMLWriter writer = new XMLWriter(file_new);
                        writer.writeDocument(document);
                        write("Content-Type: text/html");
                        write("");
                        write("<!DOCTYPE html>");
                        write("<html><head>");
                        write("<title>Successful allocating</title>");
                        write("</head><body>");
                        write("<h2>You have allocated successfully.</h2>");
                        write("<p>File is updated to store allocating details.</p>");
                        write("</body></html>");
                        writer.close();
                        break;
                    }

                    Element Team = (Element)team.get(j);
                    List<Element> student = Team.getChildren("Student");

                    for(int i = 0; i < teamSize.size(); ++i) {
                        Element Student = (Element)student.get(i);
                        Integer testingID = (i + j + teamNumber.size() / 2) % teamNumber.size();
                        if (testingID == 0) {
                            testingID = teamNumber.size();
                        }

                        String TestingID = testingID.toString();
                        Student.addContent((new Element("Testing")).setText("Team" + TestingID));
                    }

                    ++j;
                }
            } finally {
                if (reader != null) {
                    reader.close();
                }

            }
        } catch (Throwable var60) {
            if (var2 == null) {
                var2 = var60;
            } else if (var2 != var60) {
                var2.addSuppressed(var60);
            }

            throw var2;
        }

        File file_team = new File("../campus_only/xml/allocatedTeams.xml");
        var3 = null;
        reader = null;

        try {
            XMLReader reader = new XMLReader(file_team);

            try {
                Document document = reader.readDocument();
                Element RootElement = document.getRootElement();
                Content toMove = RootElement.detach();
                document.addContent((new Instruction("xml-stylesheet")).setValue("href", "teamView.xsl").setValue("type", "text/xsl"));
                document.addContent(toMove);
                File file_new = new File("../campus_only/xml/allocatedTeams_team.xml");
                file_new.createNewFile();
                XMLWriter writer = new XMLWriter(file_new);
                writer.writeDocument(document);
                writer.close();
            } finally {
                if (reader != null) {
                    reader.close();
                }

            }
        } catch (Throwable var58) {
            if (var3 == null) {
                var3 = var58;
            } else if (var3 != var58) {
                var3.addSuppressed(var58);
            }

            throw var3;
        }

        File file_assessor = new File("../campus_only/xml/allocatedTeams.xml");
        Throwable var63 = null;
        document = null;

        try {
            XMLReader reader = new XMLReader(file_assessor);

            try {
                Document document = reader.readDocument();
                Element RootElement = document.getRootElement();
                Content toMove = RootElement.detach();
                document.addContent((new Instruction("xml-stylesheet")).setValue("href", "assessorView.xsl").setValue("type", "text/xsl"));
                document.addContent(toMove);
                File file_new = new File("../campus_only/xml/allocatedTeams_assessor.xml");
                file_new.createNewFile();
                XMLWriter writer = new XMLWriter(file_new);
                writer.writeDocument(document);
                writer.close();
            } finally {
                if (reader != null) {
                    reader.close();
                }

            }

        } catch (Throwable var56) {
            if (var63 == null) {
                var63 = var56;
            } else if (var63 != var56) {
                var63.addSuppressed(var56);
            }

            throw var63;
        }
    }
}
