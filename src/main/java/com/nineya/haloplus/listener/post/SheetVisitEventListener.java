package com.nineya.haloplus.listener.post;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import com.nineya.haloplus.event.post.SheetVisitEvent;
import com.nineya.haloplus.service.SheetService;

/**
 * Sheet visit event listener.
 *
 * @author johnniang
 * @date 19-4-24
 */
@Component
public class SheetVisitEventListener extends AbstractVisitEventListener {

    protected SheetVisitEventListener(SheetService sheetService) {
        super(sheetService);
    }

    @Async
    @EventListener
    public void onSheetVisitEvent(SheetVisitEvent event) throws InterruptedException {
        handleVisitEvent(event);
    }

}
