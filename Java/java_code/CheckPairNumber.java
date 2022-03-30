package test;

import java.io.File;

public class CheckPairNumber {
    public CheckPairNumber() {
    }

    private static void write(String text) {
        System.out.println(text);
    }

    public static void main(String[] args) {
        File folder = new File("../campus_only/xml/pair/");
        File[] listOfFiles = folder.listFiles();
        int fileNumber = listOfFiles.length;
        write("Content-Type: text/html");
        write("");
        write("<!DOCTYPE hmtl>");
        write("<html><head>");
        write("<title>Result of Check</title>");
        write("</head><body>");
        write("<h2>Number of Pairs</h2>");
        write("<p>Right now in directory the number of pairs is: <strong>" + fileNumber + "</strong> , and every pair contains <strong>2</strong> students.</p>");
        write("<p>You will have 2 choices ( 4 or 6 ) to determine the <strong>size</strong> of team.<p>");
        write("<p>When generating team, please make sure every team can be full and complete.</p>");
        write("<p>In other words: &nbsp; <big><math xmlns=\"http://www.w3.org/1998/Math/MathML\"><mfrac><mrow><mo>[</mo><mi>N</mi><mi>u</mi><mi>m</mi><mi>b</mi><mi>e</mi><mi>r</mi><mo> &nbsp;  </mo><mi>o</mi><mi>f</mi><mo> &nbsp;  </mo><mi>P</mi><mi>a</mi><mi>i</mi><mi>r</mi><mi>s</mi><mo>]</mo><mo> &nbsp; </mo><mo>*</mo><mo> &nbsp; </mo><mn>2</mn></mrow><mrow><mo>[</mo><mi>S</mi><mi>i</mi><mi>z</mi><mi>e</mi><mo>]</mo></mrow></mfrac></math></big> &nbsp; should be an integer.</p>");
        write("</body></html>");
    }
}

