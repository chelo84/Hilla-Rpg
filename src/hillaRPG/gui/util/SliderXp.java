package hillaRPG.gui.util;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Slider;
import com.codename1.ui.TextArea;

public class SliderXp extends Slider {
	public SliderXp() {
		this.setUIID("SliderXp");
		this.getSliderEmptySelectedStyle().setBgColor(ColorUtil.WHITE);
		this.getSliderEmptySelectedStyle().setBgTransparency(255);
		this.getSliderEmptySelectedStyle().setBgImage(null);
		
		this.getSliderEmptyUnselectedStyle().setBgColor(ColorUtil.WHITE);
		this.getSliderEmptyUnselectedStyle().setBgTransparency(255);
		this.getSliderEmptyUnselectedStyle().setBgImage(null);
		
		this.getSliderFullSelectedStyle().setBgColor(0xFFFF00);
		this.getSliderFullSelectedStyle().setBgTransparency(255);
		
		this.getSliderFullUnselectedStyle().setBgColor(0xFFFF00);
		this.getSliderFullUnselectedStyle().setBgTransparency(255);
		
		this.getAllStyles().setAlignment(TextArea.CENTER);
	}
}
