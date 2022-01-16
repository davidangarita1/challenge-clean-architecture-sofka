package co.com.sofka.useCases;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class testJsoup {
    public static void main(String[] args) {
        final String baseURL = "https://pelisplus.so/estrenos";

        try {
            final Document document = Jsoup.connect(baseURL).get();

            for (Element row : document.select(".items-peliculas .item-pelicula a")) {
                //System.out.println(row.attr("href"));
                final String urlPelicula = row.attr("href");

                try {
                    final Document movie = Jsoup.connect("https://pelisplus.so" + urlPelicula).get();

                    String nombre = movie.select(".info-content h1").text();
                    String genero = movie.select(".info-content p:nth-of-type(4) span:nth-of-type(2)").text();
                    String sinopsis = movie.select(".sinopsis").text();
                    String fecha = movie.select(".info-content p:nth-of-type(2) span:nth-of-type(2)").text();
                    String link = movie.select(".player.player-normal ul:nth-of-type(2)  li:nth-of-type(1)").attr("data-video");
                    System.out.println(fecha);

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
