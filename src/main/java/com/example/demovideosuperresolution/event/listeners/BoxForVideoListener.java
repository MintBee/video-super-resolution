package com.example.demovideosuperresolution.event.listeners;

import com.example.demovideosuperresolution.event.VideoAssemblyEvent;
import com.example.demovideosuperresolution.event.VideoDisassemblyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class BoxForVideoListener {
    @EventListener
    void assemblyHandler(VideoAssemblyEvent event) {

    }

    void disassemblyHandler(VideoDisassemblyEvent event) {

    }


}
