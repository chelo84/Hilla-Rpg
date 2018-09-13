package hillaRPG.gui.util;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Slider;

public class SliderMana extends Slider {
	public SliderMana() {
		this.setUIID("SliderMana");
		this.getSliderEmptySelectedStyle().setBgColor(ColorUtil.WHITE);
		this.getSliderEmptySelectedStyle().setBgTransparency(255);
		this.getSliderEmptySelectedStyle().setBgImage(null);
		
		this.getSliderEmptyUnselectedStyle().setBgColor(ColorUtil.WHITE);
		this.getSliderEmptyUnselectedStyle().setBgTransparency(255);
		this.getSliderEmptyUnselectedStyle().setBgImage(null);
		
		this.getSliderFullSelectedStyle().setBgColor(0x0066CC);
		this.getSliderFullSelectedStyle().setBgTransparency(255);
		
		this.getSliderFullUnselectedStyle().setBgColor(0x0066CC);
		this.getSliderFullUnselectedStyle().setBgTransparency(255);
	}
}
