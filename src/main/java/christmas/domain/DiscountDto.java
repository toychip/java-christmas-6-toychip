package christmas.domain;

import christmas.domain.date.DecemberCalendar;
import christmas.domain.date.VisitDate;
import christmas.domain.discount.ChristmasDiscount;
import christmas.domain.discount.GiftMenuEvent;
import christmas.domain.discount.SpecialDiscount;
import christmas.domain.discount.WeekdayDiscount;
import christmas.domain.discount.WeekendDiscount;
import christmas.domain.menu.component.Price;
import christmas.domain.order.Orders;

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

    protected static ChristmasDiscount judgeXmasDiscount(VisitDate visitDate) {
        int date = visitDate.getDate();
        if (date <= 25) {
            return new ChristmasDiscount(visitDate);
        }
        return null;
    }

    protected static GiftMenuEvent judgeGiftMenuEvent(Price price){
        if (price.getValue() >= 120000) {
            return new GiftMenuEvent(price);
        }
        return null;
    }

    protected static SpecialDiscount judgeSpecialDiscount(VisitDate visitDate) {
        if (DecemberCalendar.matchStar(visitDate)) {
            return new SpecialDiscount(visitDate);
        }
        return null;
    }

    protected static WeekdayDiscount judgeWeekdayDiscount(VisitDate visitDate, Orders orders) {
        if (DecemberCalendar.matchWeekday(visitDate)) {
            return new WeekdayDiscount(visitDate, orders);
        }
        return null;
    }

    protected static WeekendDiscount judgeWeekendDiscount(VisitDate visitDate, Orders orders) {
        if (DecemberCalendar.matchWeekend(visitDate)) {
            return new WeekendDiscount(visitDate, orders);
        }
        return null;
    }

    public ChristmasDiscount getChristmasDiscount() {
        return christmasDiscount;
    }

    public GiftMenuEvent getGiftMenuEvent() {
        return giftMenuEvent;
    }

    public SpecialDiscount getSpecialDiscount() {
        return specialDiscount;
    }

    public WeekdayDiscount getWeekdayDiscount() {
        return weekdayDiscount;
    }

    public WeekendDiscount getWeekendDiscount() {
        return weekendDiscount;
    }


}
