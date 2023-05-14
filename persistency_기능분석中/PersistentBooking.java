/*
 * Restaurant Booking System: example code to accompany
 *
 * "Practical Object-oriented Design with UML"
 * Mark Priestley
 * McGraw-Hill (2004)
 */

package booksys.application.persistency ;

import booksys.application.domain.* ;


//인터페이스 정의
//예약에 대한 정수 ID를 반환하는 메서드인 getId()를 선언한다.
interface PersistentBooking extends Booking
{
  int getId() ;
}
