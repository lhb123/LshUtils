package com.linsh.lshutils.module;

import com.linsh.lshutils.utils.LshDateUtils;
import com.linsh.lshutils.utils.LshLunarCalendarUtils;

import java.util.Date;

/**
 * Created by Senh Linsh on 17/9/8.
 */

public class SimpleDate {

    private int[] mDate;

    private boolean mIsLunar;

    public SimpleDate(long date) {
        this(new Date(date));
    }

    public SimpleDate(Date date) {
        this(date, false);
    }

    public SimpleDate(Date date, boolean isLunar) {
        this(date.getYear() + 1900, date.getMonth() + 1, date.getDate(), isLunar);
    }

    public SimpleDate(int year, int month, int day) {
        this(year, month, day, false);
    }

    public SimpleDate(int year, int month, int day, boolean isLunar) {
        mDate = new int[3];
        mDate[0] = year;
        mDate[1] = month;
        mDate[2] = day;
        mIsLunar = isLunar;
    }

    public boolean isLunar() {
        return mIsLunar;
    }

    public SimpleDate setLunar(boolean isLunar) {
        mIsLunar = isLunar;
        return this;
    }

    public int getYear() {
        return mDate[0];
    }

    public SimpleDate setYear(int year) {
        mDate[0] = year;
        return this;
    }

    public int getMonth() {
        return mDate[1];
    }

    public SimpleDate setMonth(int month) {
        mDate[1] = month;
        return this;
    }

    public int getDay() {
        return mDate[2];
    }

    public SimpleDate setDay(int day) {
        mDate[2] = day;
        return this;
    }

    public Date getDate() {
        return new Date(mDate[0] - 1900, mDate[1] - 1, mDate[2]);
    }

    public String getNormalizedString() {
        return getNormalizedString(true);
    }

    public String getNormalizedString(boolean hasYear) {
        return LshDateUtils.getNormalizedStr(hasYear ? mDate[0] : 0, mDate[1], mDate[2]);
    }

    public String getDisplayString() {
        return getDisplayString(true);
    }

    public String getDisplayString(boolean hasYear) {
        if (mIsLunar) {
            return LshLunarCalendarUtils.getLunarStr(hasYear ? mDate[0] : 0, mDate[1], mDate[2]);
        }
        return LshDateUtils.getDisplayStr(hasYear ? mDate[0] : 0, mDate[1], mDate[2]);
    }

    public boolean isSameDay(SimpleDate compare) {
        return isSameDay(compare, false);
    }

    public boolean isSameDay(SimpleDate compare, boolean ignoreLunar) {
        return compare != null && getYear() == compare.getYear() && getMonth() == compare.getMonth()
                && getDay() == compare.getDay() && (ignoreLunar || isLunar() == compare.isLunar());
    }

    public static SimpleDate parseDateString(String date) {
        SimpleDate simpleDate = LshLunarCalendarUtils.parseNormalizedStr(date);
        if (simpleDate == null) {
            simpleDate = LshLunarCalendarUtils.parseLunarStr(date);
        }
        return simpleDate;
    }

    public static boolean isEqual(SimpleDate src, SimpleDate dest) {
        return isEqual(src, dest, false);
    }

    public static boolean isEqual(SimpleDate src, SimpleDate dest, boolean ignoreLunar) {
        if (src == null && dest == null) return true;
        if (src == null || dest == null) return false;
        return src.getYear() == dest.getYear() && src.getMonth() == dest.getMonth()
                && src.getDay() == dest.getDay() && (ignoreLunar || src.isLunar() == dest.isLunar());
    }
}
