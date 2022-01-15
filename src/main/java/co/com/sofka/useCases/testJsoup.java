package co.com.sofka.useCases;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class testJsoup {
    public static void main(String[] args) {
        final String url = "https://pelisplushd.net/";

        try {
            final Document document = Jsoup.connect(url).get();
            for (Element row : document.select(".Posters a")) {
                System.out.println(row.attr("href"));
                /*if (row.select("a:nth-of-type(1)").hasAttr("href")) {
                    continue;
                }
                else {
                    final String ticker =
                            row.select("td:nth-of-type(1)").text();
                    final String name =
                            row.select("td:nth-of-type(2)").text();
                    final String tempPrice =
                            row.select("td.right:nth-of-type(3)").text();
                    final String tempPrice1 =
                            tempPrice.replace(",", "");
//                    final double price = Double.parseDouble(tempPrice1);

                    System.out.println(ticker + " " + name + " " + tempPrice1);
                }*/
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
