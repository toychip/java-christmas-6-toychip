package christmas.domain;

import static christmas.static_class.DiscountStatic.GIFT_EVENT_CRITERIA_VALUE;
import static christmas.static_class.DiscountStatic.XMAS_DATE;

import christmas.domain.date.DecemberCalendar;
import christmas.domain.date.VisitDate;
import christmas.domain.discount.ChristmasDiscount;
import christmas.domain.discount.GiftMenuEvent;
import christmas.domain.discount.SpecialDiscount;
import christmas.domain.discount.WeekdayDiscount;
import christmas.domain.discount.WeekendDiscount;
import christmas.domain.menu.component.Price;
import christmas.domain.order.Orders;

public record DiscountDto(ChristmasDiscount christmasDiscount, GiftMenuEvent giftMenuEvent,
                          SpecialDiscount specialDiscount, WeekdayDiscount weekdayDiscount,
                          WeekendDiscount weekendDiscount) {

    static ChristmasDiscount judgeXmasDiscount(VisitDate visitDate) {
        int date = visitDate.getDate();
        if (date <= XMAS_DATE) {
            return new ChristmasDiscount(visitDate);
        }
        return null;
    }

    static GiftMenuEvent judgeGiftMenuEvent(Price price) {
        if (price.getValue() >= GIFT_EVENT_CRITERIA_VALUE) {
            return new GiftMenuEvent(price);
        }
        return null;
    }

    static SpecialDiscount judgeSpecialDiscount(VisitDate visitDate) {
        if (DecemberCalendar.matchStar(visitDate)) {
            return new SpecialDiscount(visitDate);
        }
        return null;
    }

    static WeekdayDiscount judgeWeekdayDiscount(VisitDate visitDate, Orders orders) {
        if (DecemberCalendar.matchWeekday(visitDate)) {
            return new WeekdayDiscount(visitDate, orders);
        }
        return null;
    }

    static WeekendDiscount judgeWeekendDiscount(VisitDate visitDate, Orders orders) {
        if (DecemberCalendar.matchWeekend(visitDate)) {
            return new WeekendDiscount(visitDate, orders);
        }
        return null;
    }

}
