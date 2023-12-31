/*
 * This is a port of the Swiss Ephemeris Free Edition, Version 1.64.01 of
 * Astrodienst AG, Switzerland from the original C Code to Java. For copyright
 * see the original copyright notices below and additional copyright notes in
 * the file named LICENSE, or - if this file is not available - the copyright
 * notes at http://www.astro.ch/org.athomeprojects.swisseph/ and following. For
 * any questions or comments regarding this port to Java, you should ONLY
 * contact me and not Astrodienst, as the Astrodienst AG is not involved in this
 * port in any way. Thomas Mack, mack@idb.cs.tu-bs.de, 23th of April 2001
 */
/*
 * Copyright (C) 1997 - 2000 Astrodienst AG, Switzerland. All rights reserved.
 * This file is part of Swiss Ephemeris Free Edition. Swiss Ephemeris is
 * distributed with NO WARRANTY OF ANY KIND. No author or distributor accepts
 * any responsibility for the consequences of using it, or for whether it serves
 * any particular purpose or works at all, unless he or she says so in writing.
 * Refer to the Swiss Ephemeris Public License ("SEPL" or the "License") for
 * full details. Every copy of Swiss Ephemeris must include a copy of the
 * License, normally in a plain ASCII text file named LICENSE. The License
 * grants you the right to copy, modify and redistribute Swiss Ephemeris, but
 * only under certain conditions described in the License. Among other things,
 * the License requires that the copyright notices and this notice be preserved
 * on all copies. For uses of the Swiss Ephemeris which do not fall under the
 * definitions laid down in the Public License, the Swiss Ephemeris Professional
 * Edition must be purchased by the developer before he/she distributes any of
 * his software or makes available any product or service built upon the use of
 * the Swiss Ephemeris. Authors of the Swiss Ephemeris: Dieter Koch and Alois
 * Treindl The authors of Swiss Ephemeris have no control or influence over any
 * of the derived works, i.e. over software or services created by other
 * programmers which use Swiss Ephemeris functions. The names of the authors or
 * of the copyright holder (Astrodienst) must not be used for promoting any
 * software, product or service which uses or contains the Swiss Ephemeris. This
 * copyright notice is the ONLY place where the names of the authors can legally
 * appear, except in cases where they have given special permission in writing.
 * The trademarks 'Swiss Ephemeris' and 'Swiss Ephemeris inside' may be used for
 * promoting such software, products or services.
 */
package org.athomeprojects.swisseph;

/**
 * This class is a date class specialized for the use with the
 * org.athomeprojects.swisseph package. You will like to use it, if you need a
 * Julian Day number or the deltaT for a date or a Julian Day or if like to
 * convert from Gregorian to Julian calendar system or vice versa.
 * <P>
 * This is a port of the SwissEphemeris package to Java. See <A
 * HREF="http://www.astro.ch">Astrodienst Z&uuml;rich </A> for more infos and
 * the original authors.
 * <P>
 * <I><B>You will find the complete documentation for the original
 * SwissEphemeris package at <A
 * HREF="http://www.astro.ch/org.athomeprojects.swisseph/sweph_g.htm">
 * http://www.astro.ch/org.athomeprojects.swisseph/sweph_g.htm </A>. By far most
 * of the information there is directly valid for this port to Java as well.
 * </B> </I>
 * 
 * @author Thomas Mack / mack@idb.cs.tu-bs.de
 * @version 1.0.0b
 */
public class SweDate {
    public static final int SUNDAY = 0;

    public static final int MONDAY = 1;

    public static final int TUESDAY = 2;

    public static final int WEDNESDAY = 3;

    public static final int THURSDAY = 4;

    public static final int FRIDAY = 5;

    public static final int SATURDAY = 6;

    public static final boolean SE_JUL_CAL = false;

    public static final boolean SE_GREG_CAL = true;

    public static final boolean SE_KEEP_DATE = true;

    public static final boolean SE_KEEP_JD = false;

    // for delta t: tidal acceleration in the mean motion of the moon
    /**
     * Tidal acceleration value in the mean motion of the moon of DE403 (-25.8).
     */
    public static final double SE_TIDAL_DE403 = -25.8;

    /**
     * Tidal acceleration value in the mean motion of the moon of DE404 (-25.8).
     */
    public static final double SE_TIDAL_DE404 = -25.8;

    /**
     * Tidal acceleration value in the mean motion of the moon of DE405
     * (-25.7376).
     */
    public static final double SE_TIDAL_DE405 = -25.7376;

    /**
     * Tidal acceleration value in the mean motion of the moon of DE406
     * (-25.7376).
     */
    public static final double SE_TIDAL_DE406 = -25.7376;

    /**
     * Tidal acceleration value in the mean motion of the moon of DE200
     * (-23.8946).
     */
    public static final double SE_TIDAL_DE200 = -23.8946;

    /**
     * Tidal acceleration value in the mean motion of the moon of -26.
     */
    public static final double SE_TIDAL_26 = -26.0;

    /**
     * Default tidal acceleration value in the mean motion of the moon
     * (=SE_TIDAL_DE406).
     * 
     * @see #SE_TIDAL_DE406
     */
    public static final double SE_TIDAL_DEFAULT = SE_TIDAL_DE406;

    /**
     * The Julian day number of 1970 January 1.0. Useful for conversions from or
     * to a Date object.
     * 
     * @see #getDate(long)
     */
    public static final double JD0 = 2440587.5; /* 1970 January 1.0 */

    static private double tid_acc = SE_TIDAL_DEFAULT;

    static private boolean init_dt_done = false;

    private double jd;

    private boolean calType;

    private int year;

    private int month;

    private int day;

    private double hour;

    private double deltaT;

    private boolean deltatIsValid = false;

    //////////////////////////////////////////////////////////////////////////////
    // Constructors
    // //////////////////////////////////////////////////////////////
    // "Throws exception"?
    // The following constructors keep julian day in favor of date:
    /**
     * This constructs a new SweDate with a default of the Julian Day number
     * 2451400.5 (10th of August 1999, 0h) and Gregorian calendar system.
     */
    public SweDate()
    {
        this(2451400.5, SE_GREG_CAL);
    }

    /**
     * This constructs a new SweDate with the given Julian Day number. The date
     * will be given in the Gregorian calendar system, even if the date is
     * before the Gregorian calendar system came into life.
     * 
     * @param jd
     *            Julian Day number
     */
    public SweDate(double jd)
    {
        this(jd, SE_GREG_CAL);
    }

    /**
     * This constructs a new SweDate with the given Julian Day number. The dates
     * will be calculated according to the given calendar system (Gregorian or
     * Julian calendar).
     * 
     * @param jd
     *            Julian Day number
     * @param calType
     *            calendar type (Gregorian or Julian calendar system)
     * @see #SE_GREG_CAL
     * @see #SE_JUL_CAL
     */
    public SweDate(double jd, boolean calType)
    {
        this.jd = jd;
        this.calType = calType;
        IDate dt = swe_revjul(jd, calType);
        this.year = dt.year;
        this.month = dt.month;
        this.day = dt.day;
        this.hour = dt.hour;
    }

    // The following constructors keep date (day, month, year) in favor of
    // julian day:
    /**
     * This constructs a new SweDate with the given date. It is assumed that the
     * date is a date in the Gregorian calendar system even if the date is prior
     * to the validity of that Gregorian calendar.
     * 
     * @param year
     *            The year of the date
     * @param month
     *            The month of the date
     * @param day
     *            The day-number of the date
     */
    public SweDate(int year, int month, int day)
    {
        this(year, month, day, 0.0, SE_GREG_CAL);
    }

    /**
     * This constructs a new SweDate with the given date and time. It is assumed
     * that the given date is a date in the Gregorian calendar system even if
     * the date is prior to the validity of that Gregorian calendar.
     * 
     * @param year
     *            The year of the date
     * @param month
     *            The month of the date
     * @param day
     *            The day-number of the date
     * @param hour
     *            The hour of the day
     */
    public SweDate(int year, int month, int day, double hour)
    {
        this(year, month, day, hour, SE_GREG_CAL);
    }

    /**
     * This constructs a new SweDate with the given date and time. The date
     * numbers will be interpreted according to the given calendar system
     * (Gregorian or Julian calendar).
     * 
     * @param year
     *            The year of the date
     * @param month
     *            The month of the date
     * @param day
     *            The day-number of the date
     * @param hour
     *            The hour of the day
     * @param calType
     *            calendar type (Gregorian or Julian calendar system)
     * @see #SE_GREG_CAL
     * @see #SE_JUL_CAL
     */
    public SweDate(int year, int month, int day, double hour, boolean calType)
    {
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.calType = calType;
        this.jd = swe_julday(year, month, day, hour, calType);
    }

    // End of constructors
    //////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////
    // Public methods:
    // ///////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////
    // Access to private variables
    // ///////////////////////////////////////////////
    // Read access: //
    /**
     * Queries the Julian Day number of this object.
     * 
     * @return Julian Day number
     */
    public double getJulDay()
    {
        return this.jd;
    }

    /**
     * Queries the Julian Day number of the given date in Gregorian calendar
     * system - this is a static method.
     * 
     * @param year
     *            The year of the date
     * @param month
     *            The month of the date
     * @param day
     *            The day-number of the date
     * @return Julian Day number
     */
    public static double getJulDay(int year, int month, int day)
    {
        return swe_julday(year, month, day, 0.0, SE_GREG_CAL);
    }

    /**
     * Queries the Julian Day number of the given date in Gregorian calendar
     * system - this is a static method.
     * 
     * @param year
     *            The year of the date
     * @param month
     *            The month of the date
     * @param day
     *            The day-number of the date
     * @param hour
     *            The hour of the day
     * @return Julian Day number
     */
    public static double getJulDay(int year, int month, int day, double hour)
    {
        return swe_julday(year, month, day, hour, SE_GREG_CAL);
    }

    /**
     * Queries the Julian Day number of the given date that is interpreted as a
     * date in the given calendar system - this is a static method.
     * 
     * @param year
     *            The year of the date
     * @param month
     *            The month of the date
     * @param day
     *            The day-number of the date
     * @param hour
     *            The hour of the day
     * @param calType
     *            calendar type (Gregorian or Julian calendar system)
     * @return Julian Day number
     * @see #SE_GREG_CAL
     * @see #SE_JUL_CAL
     */
    public static double getJulDay(int year, int month, int day, double hour,
            boolean calType)
    {
        return swe_julday(year, month, day, hour, calType);
    }

    // 0=Sunday, 1=Monday etc.
    /**
     * Queries the day of the week, i.e. Sunday to Saturday as represented by an
     * integer. Sunday is represented by 0, Saturday by 6. <B>Attention: the
     * numbers are different from the numbers returned by the java.awt.Calendar
     * class! </B>
     * 
     * @return Number of the day of week
     * @see #SUNDAY
     * @see #MONDAY
     * @see #TUESDAY
     * @see #WEDNESDAY
     * @see #THURSDAY
     * @see #FRIDAY
     * @see #SATURDAY
     */
    public int getDayOfWeekNr()
    {
        return ((int) (this.jd - 5.5)) % 7;
    }

    /**
     * Queries the day of the week of the given Julian Day number - this is a
     * static method. Sunday is represented by 0, Saturday by 6. <B>Attention:
     * the numbers are different from the numbers returned by the
     * java.awt.Calendar class! </B>
     * 
     * @param jd
     *            The Julian Day number of the date
     * @return Number of the day of week
     * @see #SUNDAY
     * @see #MONDAY
     * @see #TUESDAY
     * @see #WEDNESDAY
     * @see #THURSDAY
     * @see #FRIDAY
     * @see #SATURDAY
     */
    public static synchronized int getDayOfWeekNr(double jd)
    {
        return ((int) (jd - 5.5)) % 7;
    }

    /**
     * Queries the day of the week of the given date that is interpreted as
     * being a date in the Gregorian calendar system - this is a static method.
     * Sunday is represented by 0, Saturday by 6. <B>Attention: the numbers are
     * different from the numbers returned by the java.awt.Calendar class! </B>
     * 
     * @return Number of the day of week
     * @param year
     *            The year of the date
     * @param month
     *            The month of the date
     * @param day
     *            The day-number of the date
     * @see #SUNDAY
     * @see #MONDAY
     * @see #TUESDAY
     * @see #WEDNESDAY
     * @see #THURSDAY
     * @see #FRIDAY
     * @see #SATURDAY
     */
    public static int getDayOfWeekNr(int year, int month, int day)
    {
        return ((int) (swe_julday(year, month, day, 0.0, SE_GREG_CAL) - 5.5)) % 7;
    }

    /**
     * Queries the day of the week of the given date that is interpreted as
     * being a date in the given calendar system - this is a static method.
     * Sunday is represented by 0, Saturday by 6. <B>Attention: the numbers are
     * different from the numbers returned by the java.awt.Calendar class! </B>
     * 
     * @return Number of the day of week
     * @param year
     *            The year of the date
     * @param month
     *            The month of the date
     * @param day
     *            The day-number of the date
     * @param calType
     *            calendar type (Gregorian or Julian calendar system)
     * @see #SE_GREG_CAL
     * @see #SE_JUL_CAL
     * @see #SUNDAY
     * @see #MONDAY
     * @see #TUESDAY
     * @see #WEDNESDAY
     * @see #THURSDAY
     * @see #FRIDAY
     * @see #SATURDAY
     */
    public static int getDayOfWeekNr(int year, int month, int day,
            boolean calType)
    {
        return ((int) (swe_julday(year, month, day, 0.0, calType) - 5.5)) % 7;
    }

    /**
     * Queries the type of calendar in effect - Gregorian or Julian calendar.
     * This will effect what date you will get back for a given Julian Day.
     * 
     * @return Calendar type
     * @see #SE_GREG_CAL
     * @see #SE_JUL_CAL
     */
    public boolean getCalendarType()
    {
        return this.calType;
    }

    /**
     * Queries the year of this SweDate object.
     * 
     * @return year
     */
    public int getYear()
    {
        return this.year;
    }

    //  int getYear(double jd /*, boolean calType ?*/) { swe_revjul(jd,calType);
    // }
    /**
     * Queries the month of this SweDate object.
     * 
     * @return month <B>Attention: </B> The month ranges from 1 to 12, this is
     *         different to the java.util.Calendar class!
     */
    public int getMonth()
    {
        return this.month;
    }

    /**
     * Queries the day of this SweDate object.
     * 
     * @return day number
     */
    public int getDay()
    {
        return this.day;
    }

    /**
     * Queries the hour of the day of this SweDate object.
     * 
     * @return hour
     */
    public double getHour()
    {
        return this.hour;
    }

    /**
     * Queries the delta T value for the date of this object.
     * 
     * @return delta T
     */
    public double getDeltaT()
    {
        if (deltatIsValid) {
            return this.deltaT;
        }
        this.deltaT = calc_deltaT(this.getJulDay());
        deltatIsValid = true;
        return this.deltaT;
    }

    /**
     * Queries the delta T value for the given Julian Day number - this is a
     * static method. delta T is calculated with a tidal acceleration of
     * SE_TIDAL_DEFAULT.
     * 
     * @param tjd
     *            Julian Day number
     * @return delta T
     * @see #SE_TIDAL_DEFAULT
     */
    public static double getDeltaT(double tjd)
    {
        return calc_deltaT(tjd, SE_TIDAL_DEFAULT);
    }

    /**
     * This will return a java.util.Date object with the current date of this
     * SweDate object. This is needed often in internationalisation of date and
     * time formats. You can add an offset in milliseconds to account for
     * timezones or daylight savings time, as SweDate is meant to be in GMT time
     * always.
     * 
     * @param offset
     *            An offset in milliseconds to be added to the current date and
     *            time.
     */
    public java.util.Date getDate(long offset)
    {
        long millis = (long) ((getJulDay() - JD0) * 24L * 3600L * 1000L)
                + offset;
        return new java.util.Date(millis);
    }

    // End of read access //
    // Write access: //
    /**
     * Sets the new Julian Day for this object.
     * 
     * @param newJD
     *            Julian Day number
     */
    public void setJulDay(double newJD)
    {
        this.jd = newJD;
        deltatIsValid = false;
        IDate dt = swe_revjul(newJD, this.calType);
        this.year = dt.year;
        this.month = dt.month;
        this.day = dt.day;
        this.hour = dt.hour;
    }

    // behaelt den JD bei ODER Tag, Monat, Jahr.
    /**
     * Sets the calendar type for this object.
     * 
     * @param newCalType
     *            Calendar type (Greogorian or Julian calendar)
     * @param keepDate
     *            Determines, if the date or the julian day should be fix in
     *            this operation.
     * @see #SE_GREG_CAL
     * @see #SE_JUL_CAL
     * @see #SE_KEEP_DATE
     * @see #SE_KEEP_JD
     */
    public void setCalendarType(boolean newCalType, boolean keepDate)
    {
        if (this.calType != newCalType) {
            this.calType = newCalType;
            deltatIsValid = false;
            if (keepDate) {
                this.jd = swe_julday(this.year, this.month, this.day,
                        this.hour, this.calType);
            } else {
                IDate dt = swe_revjul(this.jd, newCalType);
                this.year = dt.year;
                this.month = dt.month;
                this.day = dt.day;
                this.hour = dt.hour;
            }
        }
    }

    // Datum:
    /**
     * Sets a new date for this object.
     * 
     * @param newYear
     *            the year-part of the new date
     * @param newMonth
     *            the month-part of the new date [1-12]
     * @param newDay
     *            the day-part of the new date [1-31]
     * @return true
     */
    public boolean setDate(int newYear, int newMonth, int newDay)
    {
        this.year = newYear;
        this.month = newMonth;
        this.day = newDay;
        deltatIsValid = false;
        this.jd = swe_julday(this.year, this.month, this.day, this.hour,
                this.calType);
        return true;
    }

    /**
     * Sets a new date for this object.
     * 
     * @param newYear
     *            the year-part of the new date
     * @param newMonth
     *            the month-part of the new date [1-12]
     * @param newDay
     *            the day-part of the new date [1-31]
     * @param newHour
     *            the hour of the new date
     * @return true
     */
    public boolean setDate(int newYear, int newMonth, int newDay, double newHour)
    {
        this.year = newYear;
        this.month = newMonth;
        this.day = newDay;
        this.hour = newHour;
        deltatIsValid = false;
        this.jd = swe_julday(this.year, this.month, this.day, this.hour,
                this.calType); // -> erzeugt JD
        return true;
    }

    /**
     * Sets a new date for this object. The input can be checked, if it is a
     * valid date and can be modified, if not. See parameter "check".
     * 
     * @param newYear
     *            the year-part of the new date
     * @param newMonth
     *            the month-part of the new date [1-12]
     * @param newDay
     *            the day-part of the new date [1-31]
     * @param check
     *            to see, if the new date is a valid date
     * @return true, if check==true, otherwise return true only, if the date is
     *         valid
     */
    public boolean setDate(int newYear, int newMonth, int newDay, boolean check)
    {
        this.year = newYear;
        double oldMonth = this.month;
        double oldDay = this.day;
        this.month = newMonth;
        this.day = newDay;
        deltatIsValid = false;
        this.jd = swe_julday(this.year, this.month, this.day, this.hour,
                this.calType); // -> erzeugt JD
        if (check) {
            IDate dt = swe_revjul(this.jd, this.calType); // -> erzeugt neues
            // Datum
            this.year = dt.year;
            this.month = dt.month;
            this.day = dt.day;
            this.hour = dt.hour;
            return (this.year == newYear && this.month == oldMonth && this.day == oldDay);
        }
        return true;
    }

    /**
     * Sets a new date for this object. The input can be checked, if it is a
     * valid date and can be modified, if not. See parameter "check".
     * 
     * @param newYear
     *            the year-part of the new date
     * @param newMonth
     *            the month-part of the new date [1-12]
     * @param newDay
     *            the day-part of the new date [1-31]
     * @param newHour
     *            the hour of the new date
     * @param check
     *            to see, if the new date is a valid date
     * @return true, if check==true, otherwise return true only, if the date is
     *         valid
     */
    public boolean setDate(int newYear, int newMonth, int newDay,
            double newHour, boolean check)
    {
        this.year = newYear;
        double oldMonth = this.month;
        double oldDay = this.day;
        this.month = newMonth;
        this.day = newDay;
        this.hour = newHour;
        deltatIsValid = false;
        this.jd = swe_julday(this.year, this.month, this.day, this.hour,
                this.calType); // -> erzeugt JD
        if (check) {
            IDate dt = swe_revjul(this.jd, this.calType); // -> erzeugt neues
            // Datum
            this.year = dt.year;
            this.month = dt.month;
            this.day = dt.day;
            this.hour = dt.hour;
            return (this.year == newYear && this.month == oldMonth && this.day == oldDay);
        }
        return true;
    }

    // Jahr:
    /**
     * Sets the year-part of the date.
     * 
     * @param newYear
     *            The new year
     * @return true
     */
    public boolean setYear(int newYear)
    {
        this.year = newYear;
        deltatIsValid = false;
        this.jd = swe_julday(this.year, this.month, this.day, this.hour,
                this.calType); // -> erzeugt JD
        return true;
    }

    /**
     * Sets the year-part of the date. The input can be checked, if the result
     * is a valid date and can be modified, if not. E.g., the date was 29th of
     * february 2000, and the year gets set to 2001. 2001 does not have a 29th
     * of february, so if parameter check is set to true, it will return false
     * and modify the date to 1st of march 2001.
     * 
     * @param newYear
     *            The new year
     * @param check
     *            check, if the resulting new date is a valid date and adjust
     *            the values for day, month or year if necessary
     * @return true, if check==true, otherwise return true only, if the date is
     *         valid
     */
    public boolean setYear(int newYear, boolean check)
    {
        this.year = newYear;
        deltatIsValid = false;
        this.jd = swe_julday(this.year, this.month, this.day, this.hour,
                this.calType); // -> erzeugt JD
        if (check) {
            double oldMonth = this.month;
            double oldDay = this.day;
            IDate dt = swe_revjul(this.jd, this.calType); // -> erzeugt neues
            // Datum
            this.year = dt.year;
            this.month = dt.month;
            this.day = dt.day;
            this.hour = dt.hour;
            return (this.year == newYear && this.month == oldMonth && this.day == oldDay);
        }
        return true;
    }

    /**
     * Sets the month-part of the date.
     * 
     * @param newMonth
     *            The new month
     * @return true
     */
    // Monat:
    public boolean setMonth(int newMonth)
    {
        this.month = newMonth;
        deltatIsValid = false;
        this.jd = swe_julday(this.year, this.month, this.day, this.hour,
                this.calType); // -> erzeugt JD
        return true;
    }

    /**
     * Sets the year-part of the date. The input can be checked, if the result
     * is a valid date and can be modified, if not.
     * 
     * @param newMonth
     *            The new year
     * @param check
     *            check, if the resulting new date is a valid date and adjust
     *            the values for day, month or year if necessary
     * @return true, if check==true, otherwise return true only, if the date is
     *         valid
     * @see SweDate#setYear(int, boolean)
     */
    public boolean setMonth(int newMonth, boolean check)
    {
        this.month = newMonth;
        deltatIsValid = false;
        this.jd = swe_julday(this.year, this.month, this.day, this.hour,
                this.calType); // -> erzeugt JD
        if (check) {
            double oldYear = this.year;
            double oldDay = this.day;
            IDate dt = swe_revjul(this.jd, this.calType); // -> erzeugt neues
            // Datum
            this.year = dt.year;
            this.month = dt.month;
            this.day = dt.day;
            this.hour = dt.hour;
            return (this.year == oldYear && this.month == newMonth && this.day == oldDay);
        }
        return true;
    }

    // Tag:
    /**
     * Sets the day-part of the date.
     * 
     * @param newDay
     *            The new day
     * @return true
     */
    public boolean setDay(int newDay)
    {
        this.day = newDay;
        deltatIsValid = false;
        this.jd = swe_julday(this.year, this.month, this.day, this.hour,
                this.calType);
        return true;
    }

    /**
     * Sets the day-part of the date. The input can be checked, if the result is
     * a valid date and can be modified, if not.
     * 
     * @param newDay
     *            The new day
     * @param check
     *            check, if the resulting new date is a valid date and adjust
     *            the values for day, month or year if necessary
     * @return true, if check==true, otherwise return true only, if the date is
     *         valid
     * @see SweDate#setYear(int, boolean)
     */
    public boolean setDay(int newDay, boolean check)
    {
        this.day = newDay;
        deltatIsValid = false;
        this.jd = swe_julday(this.year, this.month, this.day, this.hour,
                this.calType); // -> erzeugt JD
        if (check) {
            double oldYear = this.year;
            double oldMonth = this.month;
            IDate dt = swe_revjul(this.jd, this.calType); // -> erzeugt neues
            // Datum
            this.year = dt.year;
            this.month = dt.month;
            this.day = dt.day;
            this.hour = dt.hour;
            return (this.year == oldYear && this.month == oldMonth && this.day == newDay);
        }
        return true;
    }

    // Uhrzeit:
    /**
     * Sets a new hour.
     * 
     * @param newHour
     *            The new hour
     * @return true
     */
    public boolean setHour(double newHour)
    {
        this.hour = newHour;
        this.jd = swe_julday(this.year, this.month, this.day, this.hour,
                this.calType);
        return true;
    }

    // Datum ueberpruefen:
    /**
     * Checks the date to see, if it is a valid date.
     * 
     * @return true, if the date is valid, false, if not
     */
    public boolean checkDate()
    {
        return checkDate(this.year, this.month, this.day, this.hour);
    }

    /**
     * Checks the given date to see, if it is a valid date.
     * 
     * @param year
     *            the year, for which is to be checked
     * @param month
     *            the month, for which is to be checked
     * @param day
     *            the day, for which is to be checked
     * @return true, if the date is valid, false, if not
     */
    public boolean checkDate(int year, int month, int day)
    {
        return checkDate(year, month, day, 0.0);
    }

    /**
     * Checks the given date to see, if it is a valid date.
     * 
     * @param year
     *            the year, for which is to be checked
     * @param month
     *            the month, for which is to be checked
     * @param day
     *            the day, for which is to be checked
     * @param hour
     *            the hour, for which is to be checked
     * @return true, if the date is valid, false, if not
     */
    public boolean checkDate(int year, int month, int day, double hour)
    {
        double jd = swe_julday(year, month, day, hour, SE_GREG_CAL);
        IDate dt = swe_revjul(jd, SE_GREG_CAL);
        return (dt.year == year && dt.month == month && dt.day == day);
    }

    // Jimmy: add calendar type support
    public boolean checkDate(int year, int month, int day, double hour,
            boolean cal_type)
    {
        double jd = swe_julday(year, month, day, hour, cal_type);
        IDate dt = swe_revjul(jd, cal_type);
        return (dt.year == year && dt.month == month && dt.day == day);
    }

    /**
     * Makes the date to be a valid date.
     */
    public void makeValidDate()
    {
        double jd = swe_julday(this.year, this.month, this.day, this.hour,
                SE_GREG_CAL);
        IDate dt = swe_revjul(jd, SE_GREG_CAL);
        this.year = dt.year;
        this.month = dt.month;
        this.day = dt.day;
        this.hour = dt.hour;
    }

    // End of access to private variables
    // ////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////
    /**
     * Returns the tidal acceleration used in calculations of delta T.
     * 
     * @return Tidal acceleration
     */
    public double getTidalAcc()
    {
        return this.tid_acc;
    }

    /**
     * Sets the tidal acceleration used in calculations of delta T.
     * 
     * @param tid_acc
     *            tidal acceleration
     * @see #SE_TIDAL_DE403
     * @see #SE_TIDAL_DE404
     * @see #SE_TIDAL_DE405
     * @see #SE_TIDAL_DE406
     * @see #SE_TIDAL_DE200
     * @see #SE_TIDAL_26
     * @see #SE_TIDAL_DEFAULT
     */
    public void setTidalAcc(double tid_acc)
    {
        this.tid_acc = tid_acc;
    }

    // End of public methods
    // /////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////
    // Private methods:
    // //////////////////////////////////////////////////////////
    static private synchronized double swe_julday(int year, int month, int day,
            double hour, boolean calType)
    {
        double jd;
        double u, u0, u1, u2;
        u = year;
        if (month < 3) {
            u -= 1;
        }
        u0 = u + 4712.0;
        u1 = month + 1.0;
        if (u1 < 4) {
            u1 += 12.0;
        }
        jd = Math.floor(u0 * 365.25) + Math.floor(30.6 * u1 + 0.000001) + day
                + hour / 24.0 - 63.5;
        if (calType == SE_GREG_CAL) {
            u2 = Math.floor(Math.abs(u) / 100) - Math.floor(Math.abs(u) / 400);
            if (u < 0.0) {
                u2 = -u2;
            }
            jd = jd - u2 + 2;
            if ((u < 0.0) && (u / 100 == Math.floor(u / 100))
                    && (u / 400 != Math.floor(u / 400))) {
                jd -= 1;
            }
        }
        return jd;
    }

    //////////////////////////////////////////////////////////////////////
    // Erzeugt aus einem jd/calType Jahr, Monat, Tag und Stunde. //
    // It does NOT change any global variables. //
    //////////////////////////////////////////////////////////////////////
    private synchronized IDate swe_revjul(double jd, boolean calType)
    {
        IDate dt = new IDate();
        double jut;
        double u0, u1, u2, u3, u4;
        u0 = jd + 32082.5;
        if (calType == SE_GREG_CAL) {
            u1 = u0 + Math.floor(u0 / 36525.0) - Math.floor(u0 / 146100.0)
                    - 38.0;
            if (jd >= 1830691.5) {
                u1 += 1;
            }
            u0 = u0 + Math.floor(u1 / 36525.0) - Math.floor(u1 / 146100.0)
                    - 38.0;
        }
        u2 = Math.floor(u0 + 123.0);
        u3 = Math.floor((u2 - 122.2) / 365.25);
        u4 = Math.floor((u2 - Math.floor(365.25 * u3)) / 30.6001);
        dt.month = (int) (u4 - 1.0);
        if (dt.month > 12) {
            dt.month -= 12;
        }
        dt.day = (int) (u2 - Math.floor(365.25 * u3) - Math.floor(30.6001 * u4));
        dt.year = (int) (u3 + Math.floor((u4 - 2.0) / 12.0) - 4800);
        dt.hour = (jd - Math.floor(jd + 0.5) + 0.5) * 24.0;
        return dt;
    }

    ////////////////////////////////////////////////////////////////////////////
    /// deltaT:
    ////////////////////////////////////////////////////////////////////////////
    /*
     * DeltaT = Ephemeris Time - Universal Time, in days. 1620 - today + a
     * couple of years: --------------------------------- The tabulated values
     * of deltaT, in hundredths of a second, were taken from The Astronomical
     * Almanac 1997, page K8. The program adjusts for a value of secular tidal
     * acceleration ndot = -25.7376. arcsec per century squared, the value used
     * in JPL's DE403 ephemeris. ELP2000 (and DE200) used the value -23.8946. To
     * change ndot, one can either redefine SE_TIDAL_DEFAULT in swephexp.h or
     * use the routine swe_set_tid_acc() before calling Swiss Ephemeris.
     * Bessel's interpolation formula is implemented to obtain fourth order
     * interpolated values at intermediate times. -500 - 1620:
     * --------------------------------- For dates between -500 and 1600, the
     * table given by Stephenson (1997; p. 515) is used, with linear
     * interpolation. This table is based on an assumed value of ndot = -26. The
     * program adjusts for ndot = -25.7376. For 1600 - 1620, a linear
     * interpolation between the last value of the latter and the first value of
     * the former table is made. before -500: ---------------------------------
     * For times before -600, a formula of Stephenson & Morrison (1995) (S.
     * Stephenson 1997; p. 508) is used: dt = 35 * t * t - 20 sec, where t is
     * centuries from 1735 AD. For -600 to -500, a transition from this formula
     * to the Stephenson table has been implemented in order to avoid a jump.
     * future: --------------------------------- For the time after the last
     * tabulated value, we use the formula of Stephenson (1997; p. 507), with a
     * modification that avoids a jump at the end of the tabulated period. A
     * linear term is added that makes a slow transition from the table to the
     * formula over a period of 100 years. (Need not be updated, when table will
     * be enlarged.) References: Stephenson, F. R., and L. V. Morrison,
     * "Long-term changes in the rotation of the Earth: 700 B.C. to A.D. 1980,"
     * Philosophical Transactions of the Royal Society of London Series A 313,
     * 47-70 (1984) Borkowski, K. M., "ELP2000-85 and the Dynamical Time -
     * Universal Time relation," Astronomy and Astrophysics 205, L8-L10 (1988)
     * Borkowski's formula is derived from partly doubtful eclipses going back
     * to 2137 BC and uses lunar position based on tidal coefficient of -23.9
     * arcsec/cy^2. Chapront-Touze, Michelle, and Jean Chapront, _Lunar Tables
     * and Programs from 4000 B.C. to A.D. 8000_, Willmann-Bell 1991 Their table
     * agrees with the one here, but the entries are rounded to the nearest
     * whole second. Stephenson, F. R., and M. A. Houlden, _Atlas of Historical
     * SearchRecord Maps_, Cambridge U. Press (1986) Stephenson, F.R. &
     * Morrison, L.V., "Long-Term Fluctuations in the Earth's Rotation: 700 BC
     * to AD 1990", Philosophical Transactions of the Royal Society of London,
     * Ser. A, 351 (1995), 165-202. Stephenson, F. Richard, _Historical Eclipses
     * and Earth's Rotation_, Cambridge U. Press (1997) Table from AA for 1620
     * through today Note, Stephenson and Morrison's table starts at the year
     * 1630. The Chapronts' table does not agree with the Almanac prior to 1630.
     * The actual accuracy decreases rapidly prior to 1780. Jean Meeus,
     * Astronomical Algorithms, 2nd edition, 1998. For a comprehensive
     * collection of publications and formulae, see:
     * http://www.phys.uu.nl/~vgent/astro/deltatime.htm Last update of table
     * dt[]: Dieter Koch, 9 Apr. 2002. ATTENTION: Whenever updating this table,
     * do not forget to adjust the macros TABEND and TABSIZ !
     */
    static private final int TABSTART = 1620;

    static private final int TABEND = 2014;

    static private final int TABSIZ = TABEND - TABSTART + 1;

    /* we make the table greater for additional values read from external file */
    static private final int TABSIZ_SPACE = TABSIZ + 50;

    static private short dt[] = new short[] {
    /* 1620.0 thru 1659.0 */
    12400, 11900, 11500, 11000, 10600, 10200, 9800, 9500, 9100, 8800, 8500,
            8200, 7900, 7700, 7400, 7200, 7000, 6700, 6500, 6300, 6200, 6000,
            5800, 5700, 5500, 5400, 5300, 5100, 5000, 4900, 4800, 4700, 4600,
            4500, 4400, 4300, 4200, 4100, 4000, 3800,
            /* 1660.0 thru 1699.0 */
            3700, 3600, 3500, 3400, 3300, 3200, 3100, 3000, 2800, 2700, 2600,
            2500, 2400, 2300, 2200, 2100, 2000, 1900, 1800, 1700, 1600, 1500,
            1400, 1400, 1300, 1200, 1200, 1100, 1100, 1000, 1000, 1000, 900,
            900, 900, 900, 900, 900, 900, 900,
            /* 1700.0 thru 1739.0 */
            900, 900, 900, 900, 900, 900, 900, 900, 1000, 1000, 1000, 1000,
            1000, 1000, 1000, 1000, 1000, 1100, 1100, 1100, 1100, 1100, 1100,
            1100, 1100, 1100, 1100, 1100, 1100, 1100, 1100, 1100, 1100, 1100,
            1200, 1200, 1200, 1200, 1200, 1200,
            /* 1740.0 thru 1779.0 */
            1200, 1200, 1200, 1200, 1300, 1300, 1300, 1300, 1300, 1300, 1300,
            1400, 1400, 1400, 1400, 1400, 1400, 1400, 1500, 1500, 1500, 1500,
            1500, 1500, 1500, 1600, 1600, 1600, 1600, 1600, 1600, 1600, 1600,
            1600, 1600, 1700, 1700, 1700, 1700, 1700,
            /* 1780.0 thru 1799.0 */
            1700, 1700, 1700, 1700, 1700, 1700, 1700, 1700, 1700, 1700, 1700,
            1700, 1600, 1600, 1600, 1600, 1500, 1500, 1400, 1400,
            /* 1800.0 thru 1819.0 */
            1370, 1340, 1310, 1290, 1270, 1260, 1250, 1250, 1250, 1250, 1250,
            1250, 1250, 1250, 1250, 1250, 1250, 1240, 1230, 1220,
            /* 1820.0 thru 1859.0 */
            1200, 1170, 1140, 1110, 1060, 1020, 960, 910, 860, 800, 750, 700,
            660, 630, 600, 580, 570, 560, 560, 560, 570, 580, 590, 610, 620,
            630, 650, 660, 680, 690, 710, 720, 730, 740, 750, 760, 770, 770,
            780, 780,
            /* 1860.0 thru 1899.0 */
            788, 782, 754, 697, 640, 602, 541, 410, 292, 182, 161, 10, -102,
            -128, -269, -324, -364, -454, -471, -511, -540, -542, -520, -546,
            -546, -579, -563, -564, -580, -566, -587, -601, -619, -664, -644,
            -647, -609, -576, -466, -374,
            /* 1900.0 thru 1939.0 */
            -272, -154, -2, 124, 264, 386, 537, 614, 775, 913, 1046, 1153,
            1336, 1465, 1601, 1720, 1824, 1906, 2025, 2095, 2116, 2225, 2241,
            2303, 2349, 2362, 2386, 2449, 2434, 2408, 2402, 2400, 2387, 2395,
            2386, 2393, 2373, 2392, 2396, 2402,
            /* 1940.0 thru 1979.0 */
            2433, 2483, 2530, 2570, 2624, 2677, 2728, 2778, 2825, 2871, 2915,
            2957, 2997, 3036, 3072, 3107, 3135, 3168, 3218, 3268, 3315, 3359,
            3400, 3447, 3503, 3573, 3654, 3743, 3829, 3920, 4018, 4117, 4223,
            4337, 4449, 4548, 4646, 4752, 4853, 4959,
            /* 1980.0 thru 1999.0 */
            5054, 5138, 5217, 5296, 5379, 5434, 5487, 5532, 5582, 5630, 5686,
            5757, 5831, 5912, 5998, 6078, 6163, 6230, 6297, 6347,
            /* 2000.0 thru 2003.0 */
            6383, 6409, 6430, 6447,
            /* Extrapolated values, 2004 - 2014 */
            6457, 6500, 6600, 6700, 6800, 6900, 7000, 7000, 7100, 7200, 7300,
            // Add 50 empty elements, see constant TABSIZ_SPACE above!
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, };

    /*
     * Table for -500 through 1600, from Stephenson & Morrison (1995). The first
     * value for -550 has been added from Borkowski in order to make this table
     * fit with the Borkowski formula for times before -550.
     */
    static private final int TAB2_SIZ = 43;

    static private final int TAB2_START = -500;

    static private final int TAB2_END = 1600;

    static private short dt2[] = new short[] {
    /* -500 -450 -400 -350 -300 -250 -200 -150 -100 -50 */
    16800, 16000, 15300, 14600, 14000, 13400, 12800, 12200, 11600, 11100,
    /* 0 50 100 150 200 250 300 350 400 450 */
    10600, 10100, 9600, 9100, 8600, 8200, 7700, 7200, 6700, 6200,
    /* 500 550 600 650 700 750 800 850 900 950 */
    5700, 5200, 4700, 4300, 3800, 3400, 3000, 2600, 2200, 1900,
    /* 1000 1050 1100 1150 1200 1250 1300 1350 1400 1450 */
    1600, 1350, 1100, 900, 750, 600, 470, 380, 300, 230,
    /* 1500 1550 1600 */
    180, 140, 110, };

    /*
     * returns DeltaT (ET - UT) in days double tjd = julian day in UT
     */
    private synchronized double calc_deltaT(double tjd)
    {
        return calc_deltaT(tjd, this.tid_acc);
    }

    static private synchronized double calc_deltaT(double tjd, double tid_acc)
    {
        double ans = 0., ans2, ans3;
        double p, B = 0., Y = 0., dd; // To remove Java warning of "maybe" not
        // initialized
        int d[] = new int[6];
        int i, iy, k;
        /* read additional values from swedelta.txt */
        int tabsiz = init_dt();
        int tabend = TABSTART + tabsiz - 1;
        Y = 2000.0 + (tjd - SwephData.J2000) / 365.25;
        /*
         * before -500: formula by Stephenson (1997; p. 508) but adjusted to fit
         * the starting point of table dt2 (Stephenson 1997).
         */
        if (Y < TAB2_START) {
            B = (Y - 1735) * 0.01;
            ans = -20 + 35 * B * B;
            ans = adjust_for_tidacc(tid_acc, ans, Y);
            /* transition from formula to table over 100 years */
            if (Y >= TAB2_START - 100) {
                /* starting value of table dt2: */
                ans2 = adjust_for_tidacc(tid_acc, dt2[0], TAB2_START);
                /* value of formula at epoch TAB2_START */
                B = (TAB2_START - 1735) * 0.01;
                ans3 = -20 + 35 * B * B;
                ans3 = adjust_for_tidacc(tid_acc, ans3, Y);
                dd = ans3 - ans2;
                B = (Y - (TAB2_START - 100)) * 0.01;
                /* fit to starting point of table dt2. */
                ans = ans - dd * B;
            }
        }
        /*
         * between -500 and 1600: linear interpolation between values of table
         * dt2 (Stephenson 1997)
         */
        if (Y >= TAB2_START && Y < TAB2_END) {
            p = Math.floor(Y);
            iy = (int) ((p - TAB2_START) / 50.0);
            dd = (Y - (TAB2_START + 50 * iy)) / 50.0;
            ans = dt2[iy] + (dt2[iy + 1] - dt2[iy]) * dd;
            /* correction for tidal acceleration used by our ephemeris */
            ans = adjust_for_tidacc(tid_acc, ans, Y);
        }
        /*
         * between 1600 and 1620: linear interpolation between end of table dt2
         * and start of table dt
         */
        if (Y >= TAB2_END && Y < TABSTART) {
            B = TABSTART - TAB2_END;
            iy = (TAB2_END - TAB2_START) / 50;
            dd = (Y - TAB2_END) / B;
            ans = dt2[iy] + dd * (dt[0] / 100.0 - dt2[iy]);
            ans = adjust_for_tidacc(tid_acc, ans, Y);
        }
        /*
         * 1620 - today + a few years (tabend): Besselian interpolation from
         * tabulated values in table dt. See AA page K11.
         */
        if (Y >= TABSTART && Y <= tabend) {
            /*
             * Index into the table.
             */
            p = Math.floor(Y);
            iy = (int) (p - TABSTART);
            /*
             * Zeroth order estimate is value at start of year
             */
            ans = dt[iy];
            k = iy + 1;
            if (k >= tabsiz)
                return deltatIsDone(ans, Y, B, tid_acc, tabsiz, tabend); /*
                                                                          * No
                                                                          * data,
                                                                          * can't
                                                                          * go
                                                                          * on.
                                                                          */
            /*
             * The fraction of tabulation interval
             */
            p = Y - p;
            /*
             * First order interpolated value
             */
            ans += p * (dt[k] - dt[iy]);
            if ((iy - 1 < 0) || (iy + 2 >= tabsiz))
                return deltatIsDone(ans, Y, B, tid_acc, tabsiz, tabend); /*
                                                                          * can't
                                                                          * do
                                                                          * second
                                                                          * differences
                                                                          */
            /*
             * Make table of first differences
             */
            k = iy - 2;
            for (i = 0; i < 5; i++) {
                if ((k < 0) || (k + 1 >= tabsiz))
                    d[i] = 0;
                else
                    d[i] = dt[k + 1] - dt[k];
                k += 1;
            }
            /*
             * Compute second differences
             */
            for (i = 0; i < 4; i++)
                d[i] = d[i + 1] - d[i];
            B = 0.25 * p * (p - 1.0);
            ans += B * (d[1] + d[2]);
            //    printf( "B %.4lf, ans %.4lf\n", B, ans );
            if (iy + 2 >= tabsiz)
                return deltatIsDone(ans, Y, B, tid_acc, tabsiz, tabend);
            /*
             * Compute third differences
             */
            for (i = 0; i < 3; i++)
                d[i] = d[i + 1] - d[i];
            B = 2.0 * B / 3.0;
            ans += (p - 0.5) * B * d[1];
            //    printf( "B %.4lf, ans %.4lf\n", B*(p-0.5), ans );
            if ((iy - 2 < 0) || (iy + 3 > tabsiz))
                return deltatIsDone(ans, Y, B, tid_acc, tabsiz, tabend);
            /*
             * Compute fourth differences
             */
            for (i = 0; i < 2; i++)
                d[i] = d[i + 1] - d[i];
            B = 0.125 * B * (p + 1.0) * (p - 2.0);
            ans += B * (d[0] + d[1]);
            //    printf( "B %.4lf, ans %.4lf\n", B, ans );
        }
        return deltatIsDone(ans, Y, B, tid_acc, tabsiz, tabend);
    }

    private synchronized static double deltatIsDone(double ans, double Y,
            double B, double tid_acc, int tabsiz, int tabend)
    {
        double ans2, ans3, B2, dd;
        if (Y >= TABSTART && Y <= tabend) {
            ans *= 0.01;
            ans = adjust_for_tidacc(tid_acc, ans, Y);
        }
        /*
         * today - : Formula Stephenson (1997; p. 507), with modification to
         * avoid jump at end of AA table, similar to what Meeus 1998 had
         * suggested. Slow transition within 100 years.
         */
        if (Y > tabend) {
            B = 0.01 * (Y - 1820);
            ans = -20 + 31 * B * B;
            /* slow transition from tabulated values to Stephenson formula: */
            if (Y <= tabend + 100) {
                B2 = 0.01 * (tabend - 1820);
                ans2 = -20 + 31 * B2 * B2;
                ans3 = dt[tabsiz - 1] * 0.01;
                dd = (ans2 - ans3);
                ans += dd * (Y - (tabend + 100)) * 0.01;
            }
        }
        return ans / 86400.0;
    }

    /*
     * Read delta t values from external file. record structure:
     * year(whitespace)delta_t in 0.01 sec.
     */
    static private int init_dt()
    {
        FilePtr fp = null;
        int year;
        int tab_index;
        int tabsiz;
        int i;
        if (!init_dt_done) {
            init_dt_done = true;
            // jimmy: comment out unneeded check
            return TABSIZ;
        }
        /* find table size */
        tabsiz = 2001 - TABSTART + 1;
        for (i = tabsiz - 1; i < TABSIZ_SPACE; i++) {
            if (dt[i] == 0)
                break;
            else
                tabsiz++;
        }
        tabsiz--;
        return tabsiz;
    }

    /*
     * Astronomical Almanac table is corrected by adding the expression
     * -0.000091 (ndot + 26)(year-1955)^2 seconds to entries prior to 1955 (AA
     * page K8), where ndot is the secular tidal term in the mean motion of the
     * Moon. Entries after 1955 are referred to atomic time standards and are
     * not affected by errors in Lunar or planetary theory.
     */
    static private double adjust_for_tidacc(double tid_acc_local, double ans,
            double Y)
    {
        double B;
        if (Y < 1955.0) {
            B = (Y - 1955.0);
            ans += -0.000091 * (tid_acc_local + 26.0) * B * B;
        }
        return ans;
    }
} // end of class SweDate

class IDate {
    public int year;

    public int month;

    public int day;

    public double hour;
}