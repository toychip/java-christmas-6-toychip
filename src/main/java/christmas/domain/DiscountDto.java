package christmas.domain;

import christmas.domain.date.DecemberCalendar;
import christmas.domain.date.VisitDate;
import christmas.domain.discount.ChristmasDiscount;
import christmas.domain.discount.GiftMenuEvent;
import christmas.domain.discount.SpecialDiscount;
import christmas.domain.discount.WeekdayDiscount;
import christmas.domain.discount.WeekendDiscount;

public class DiscountDto {

    private final ChristmasDiscount christmasDiscount;
    private final GiftMenuEvent giftMenuEvent;
    private final SpecialDiscount specialDiscount;
    private final WeekdayDiscount weekdayDiscount;
    private final WeekendDiscount weekendDiscount;

    public DiscountDto(ChristmasDiscount christmasDiscount, GiftMenuEvent giftMenuEvent,
                       SpecialDiscount specialDiscount, WeekdayDiscount weekdayDiscount,
                       WeekendDiscount weekendDiscount) {
        this.christmasDiscount = christmasDiscount;
        this.giftMenuEvent = giftMenuEvent;
        this.specialDiscount = specialDiscount;
        this.weekdayDiscount = weekdayDiscount;
        this.weekendDiscount = weekendDiscount;
    }

}
