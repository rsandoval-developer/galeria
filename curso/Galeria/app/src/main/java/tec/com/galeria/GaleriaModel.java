package tec.com.galeria;

import java.util.List;

/**
 * Created by lapprestamo on 19/11/16.
 */

public class GaleriaModel {

    private List<Imagen> galeria;

    public List<Imagen> getGaleria() {
        return galeria;
    }

    public void setGaleria(List<Imagen> galeria) {
        this.galeria = galeria;
    }

    public static class Imagen {

        private int id;
        private String url;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

}
