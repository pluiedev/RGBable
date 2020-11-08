package com.leocth.rgbable.api.v2.cca;

import com.leocth.rgbable.api.v2.Color;
import dev.onyxstudios.cca.api.v3.component.Component;
import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import org.jetbrains.annotations.Nullable;

/**
 * Represents a Cardinal Components API {@code Component} that stores color data.
 *
 * @author leocth
 * @since 2.0.0
 */
public interface ColorComponent extends Component, AutoSyncedComponent {
    @Nullable Color getColor();
    void setColor(@Nullable Color color);

    static <T> ColorComponent get(T provider) {
        return ColorComponents.COLOR.get(provider);
    }
}
