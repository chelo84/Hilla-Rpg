package hillaRPG.gui.util;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Slider;

public class SliderVida extends Slider {
	public SliderVida() {
		this.setUIID("SliderVida");
		this.getSliderEmptySelectedStyle().setBgColor(ColorUtil.WHITE);
		this.getSliderEmptySelectedStyle().setBgTransparency(255);
		this.getSliderEmptySelectedStyle().setBgImage(null);
		
		this.getSliderEmptyUnselectedStyle().setBgColor(ColorUtil.WHITE);
		this.getSliderEmptyUnselectedStyle().setBgTransparency(255);
		this.getSliderEmptyUnselectedStyle().setBgImage(null);
		
		this.getSliderFullSelectedStyle().setBgColor(ColorUtil.GREEN);
		this.getSliderFullSelectedStyle().setBgTransparency(255);
		
		this.getSliderFullUnselectedStyle().setBgColor(ColorUtil.GREEN);
		this.getSliderFullUnselectedStyle().setBgTransparency(255);
	}
}
