package edu.csus.csc131.euc;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class DayTest {

    Day day = new Day(getSampleRates());

    private static final int HOURS_IN_DAY = 24;

    @Test
    void getDate() {
        Day altDay = new Day(getSampleRates());

        LocalDate date = altDay.getDate();

        assertEquals(LocalDate.now().getYear(), date.getYear());
        assertEquals(LocalDate.now().getMonthValue(), date.getMonthValue());
        assertEquals(LocalDate.now().getDayOfMonth(), date.getDayOfMonth());
    }

    @Test
    void getDate_1() {
        Day altDay = new Day(getSampleRates(), 3);
        assertEquals(LocalDate.now().getDayOfMonth() - 3, altDay.getDate().getDayOfMonth());
    }

    @Test
    void getDate_2() {
        LocalDate date = LocalDate.parse("2020-03-02");

        Day altDay = new Day(getSampleRates(), date);
        LocalDate altDate = altDay.getDate();

        assertEquals(2020, altDate.getYear());
        assertEquals(3, altDate.getMonthValue());
        assertEquals(2, altDate.getDayOfMonth());
    }

    @Test
    void getDate_3() {
        Day altDay = new Day(getSampleRates(), "2020-03-02");

        LocalDate date = altDay.getDate();

        assertEquals(2020, date.getYear());
        assertEquals(3, date.getMonthValue());
        assertEquals(2, date.getDayOfMonth());
    }


    @Test
    void setDate() {
        LocalDate date = LocalDate.parse("2020-03-02");

        Day altDay = new Day((Rates)null);

        altDay.setDate(date);

        LocalDate altDate = altDay.getDate();

        assertEquals(2020, altDate.getYear());
        assertEquals(3, altDate.getMonthValue());
        assertEquals(2, altDate.getDayOfMonth());
    }

    @Test
    void getDateAsString(){
        Day altDay = new Day(getSampleRates(), "2020-03-02");

        assertEquals("03-02-2020", altDay.getDateAsString());
    }

    @Test
    void setRates(){

        Day altDay = new Day((Rates)null);

        altDay.setRates(getSampleRates());

        altDay.setUsage(getSampleUsage());

        assertEquals(53.6304, altDay.getDailyCost());
    }


    @Test
    void getDailyUsage() {

        double[] usageValues = getSampleUsage();

        day.setUsage(usageValues);

        assertEquals(26.8152, day.getDailyUsage());
    }

    @Test
    void getDailyUsage_1() {

        double[] usageValues = getSampleUsage();

        for(int i = 0; i < HOURS_IN_DAY; i++){
            day.setUsage(i, usageValues[i]);
        }

        assertEquals(26.8152, day.getDailyUsage());
    }

    @Test
    void getDailyUsage_2() {

        Day altDay = new Day((Rates)null);

        double[] usageValues = getSampleUsage();

        for(int i = 0; i < HOURS_IN_DAY; i++){
            altDay.setUsage(i, usageValues[i]);
        }

        assertEquals(-1.0, altDay.getDailyUsage());
    }

    @Test
    void getDailyUsage_3() {

        Usage u = new Usage();
        u.setUsage(getSampleUsage());

        Day altDay = new Day("2020-03-02", u);

        assertEquals("03-02-2020", altDay.getDateAsString());
    }


    @Test
    void getDailyCost() {

        double[] usageValues = getSampleUsage();

        day.setUsage(usageValues);

        assertEquals(26.8152 * 2, day.getDailyCost());
    }

    @Test
    void getDailyCost_1() {

        Day altDay = new Day(null);

        double[] usageValues = getSampleUsage();

        altDay.setUsage(usageValues);

        assertEquals(-1, altDay.getDailyCost());
    }


    public static Rates getSampleRates(){
        Rates rates = new Rates();

        for(int i = 0; i < HOURS_IN_DAY; i++){
            rates.setRates(i, 2.0);
        }

        return rates;
    }

    public static double[] getSampleUsage(){
        double[] out = {
                0.4272,
                0.3768,
                0.3888,
                0.3888,
                0.5604,
                0.4152,
                0.5952,
                1.5900,
                1.2336,
                1.1556,
                3.4824,
                2.8968,
                1.0452,
                0.4044,
                0.4452,
                0.5088,
                0.5580,
                0.8592,
                1.8720,
                2.2704,
                1.7436,
                1.2960,
                1.1472,
                1.1544
        };

        return out;
    }
}