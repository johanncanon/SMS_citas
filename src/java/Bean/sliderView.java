/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import javax.inject.Named;
import javax.enterprise.context.Dependent;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author SISTEMAS
 */
@Named(value = "sliderView")
@Dependent
public class sliderView {

    private List<String> images;

    /**
     * Creates a new instance of sliderView
     */
    public void init() {
        images = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            images.add("img-" + i + ".png");
        }
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

}
