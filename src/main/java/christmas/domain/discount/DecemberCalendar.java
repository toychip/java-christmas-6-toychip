package christmas.domain.discount;

import christmas.domain.VisitDate;

public enum DecemberCalendar {
    DAY_1(new VisitDate("1"), false, true, false),
    DAY_2(new VisitDate("2"), false, true, false),
    DAY_3(new VisitDate("3"), true, false, true),
    DAY_4(new VisitDate("4"), true, false, false),
    DAY_5(new VisitDate("5"), true, false, false),
    DAY_6(new VisitDate("6"), true, false, false),
    DAY_7(new VisitDate("7"), true, false, false),
    DAY_8(new VisitDate("8"), false, true, false),
    DAY_9(new VisitDate("9"), false, true, false),
    DAY_10(new VisitDate("10"), true, false, true),
    DAY_11(new VisitDate("11"), true, false, false),
    DAY_12(new VisitDate("12"), true, false, false),
    DAY_13(new VisitDate("13"), true, false, false),
    DAY_14(new VisitDate("14"), true, false, false),
    DAY_15(new VisitDate("15"), false, true, false),
    DAY_16(new VisitDate("16"), false, true, false),
    DAY_17(new VisitDate("17"), true, false, true),
    DAY_18(new VisitDate("18"), true, false, false),
    DAY_19(new VisitDate("19"), true, false, false),
    DAY_20(new VisitDate("20"), true, false, false),
    DAY_21(new VisitDate("21"), true, false, false),
    DAY_22(new VisitDate("22"), false, true, false),
    DAY_23(new VisitDate("23"), false, true, false),
    DAY_24(new VisitDate("24"), true, false, true),
    DAY_25(new VisitDate("25"), true, false, true),
    DAY_26(new VisitDate("26"), true, false, false),
    DAY_27(new VisitDate("27"), true, false, false),
    DAY_28(new VisitDate("28"), true, false, false),
    DAY_29(new VisitDate("29"), false, true, false),
    DAY_30(new VisitDate("30"), false, true, false),
    DAY_31(new VisitDate("31"), true, false, true)
    ;

    DecemberCalendar(VisitDate date, boolean weekday, boolean weekend, boolean star) {
        this.date = date;
        this.weekday = weekday;
        this.weekend = weekend;
        this.star = star;
    }

    private final VisitDate date;
    private final boolean weekday;
    private final boolean weekend;
    private final boolean star;

    public static boolean matchWeekDay(VisitDate visitDate) {
        int date = visitDate.getDate();
        for (DecemberCalendar day : DecemberCalendar.values()) {
            if (day.getDate() == date) {
                return day.isWeekday();
            }
        }
        return false;
    }

    private int getDate() {
        return date.getDate();
    }

    public boolean isWeekday() {
        return weekday;
    }

    public boolean isWeekend() {
        return weekend;
    }

    public boolean isStar() {
        return star;
    }
}
