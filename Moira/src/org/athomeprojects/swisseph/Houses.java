/*
   This is a port of the Swiss Ephemeris Free Edition, Version 1.64.01
   of Astrodienst AG, Switzerland from the original C Code to Java. For
   copyright see the original copyright notices below and additional
   copyright notes in the file named LICENSE, or - if this file is not
   available - the copyright notes at http://www.astro.ch/org.athomeprojects.swisseph/ and
   following. 

   For any questions or comments regarding this port to Java, you should
   ONLY contact me and not Astrodienst, as the Astrodienst AG is not involved
   in this port in any way.

   Thomas Mack, mack@idb.cs.tu-bs.de, 23th of April 2001

*/
/* Copyright (C) 1997 - 2000 Astrodienst AG, Switzerland.
   All rights reserved.

  This file is part of Swiss Ephemeris Free Edition.

  Swiss Ephemeris is distributed with NO WARRANTY OF ANY KIND.  No author
  or distributor accepts any responsibility for the consequences of using it,
  or for whether it serves any particular purpose or works at all, unless he
  or she says so in writing.  Refer to the Swiss Ephemeris Public License
  ("SEPL" or the "License") for full details.

  Every copy of Swiss Ephemeris must include a copy of the License,
  normally in a plain ASCII text file named LICENSE.  The License grants you
  the right to copy, modify and redistribute Swiss Ephemeris, but only
  under certain conditions described in the License.  Among other things, the
  License requires that the copyright notices and this notice be preserved on
  all copies.

  For uses of the Swiss Ephemeris which do not fall under the definitions
  laid down in the Public License, the Swiss Ephemeris Professional Edition
  must be purchased by the developer before he/she distributes any of his
  software or makes available any product or service built upon the use of
  the Swiss Ephemeris.

  Authors of the Swiss Ephemeris: Dieter Koch and Alois Treindl

  The authors of Swiss Ephemeris have no control or influence over any of
  the derived works, i.e. over software or services created by other
  programmers which use Swiss Ephemeris functions.

  The names of the authors or of the copyright holder (Astrodienst) must not
  be used for promoting any software, product or service which uses or contains
  the Swiss Ephemeris. This copyright notice is the ONLY place where the
  names of the authors can legally appear, except in cases where they have
  given special permission in writing.

  The trademarks 'Swiss Ephemeris' and 'Swiss Ephemeris inside' may be used
  for promoting such software, products or services.
*/
package org.athomeprojects.swisseph;

/**
* A class to contain all the data that are relevant to houses in org.athomeprojects.moira.
* Does not seem to be relevant outside this package...
* <P><I><B>You will find the complete documentation for the original
* SwissEphemeris package at <A HREF="http://www.astro.ch/org.athomeprojects.swisseph/sweph_g.htm">
* http://www.astro.ch/org.athomeprojects.swisseph/sweph_g.htm</A>. By far most of the information 
* there is directly valid for this port to Java as well.</B></I>
* @version 1.0.0a
*/
class Houses {
  /**
  * The twelve house cusps from cusp[1] to cusp[12] plus many additional
  * points.
  */
  double cusp[]=new double[37];
  /**
  * The double value of the ascendent.
  */
  double ac;
  /**
  * The double value of the MC (=Medium Coeli = the midpoint of the heaven).
  */
  double mc;
  /**
  * The double value of the vertex.
  */
  double vertex;
  /**
  * The double value of the "equatorial ascendant".
  */
  double equasc;
  /**
  * The double value of the "co-ascendant" (Walter Koch).
  */
  double coasc1;
  /**
  * The double value of the "co-ascendant" (Michael Munkasey).
  */
  double coasc2;
  /**
  * The double value of the "polar ascendant" (Michael Munkasey).
  */
  double polasc;

}
