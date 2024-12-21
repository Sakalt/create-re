package com.sakalti.create_re.foundation.blockEntity.behaviour;

import java.util.function.Function;

import com.sakalti.create_re.foundation.blockEntity.behaviour.ValueSettingsBehaviour.ValueSettings;
import com.sakalti.create_re.foundation.blockEntity.behaviour.scrollValue.INamedIconOptions;
import com.sakalti.create_re.foundation.gui.AllIcons;
import com.sakalti.create_re.foundation.utility.Lang;

import net.minecraft.network.chat.MutableComponent;

public class ValueSettingsFormatter {

	private Function<ValueSettings, MutableComponent> formatter;

	public ValueSettingsFormatter(Function<ValueSettings, MutableComponent> formatter) {
		this.formatter = formatter;
	}

	public MutableComponent format(ValueSettings valueSettings) {
		return formatter.apply(valueSettings);
	}

	public static class ScrollOptionSettingsFormatter extends ValueSettingsFormatter {

		private INamedIconOptions[] options;

		public ScrollOptionSettingsFormatter(INamedIconOptions[] options) {
			super(v -> Lang.translateDirect(options[v.value()].getTranslationKey()));
			this.options = options;
		}

		public AllIcons getIcon(ValueSettings valueSettings) {
			return options[valueSettings.value()].getIcon();
		}

	}

}
