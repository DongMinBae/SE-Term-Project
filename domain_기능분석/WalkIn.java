/*
 * Restaurant Booking System: example code to accompany
 *
 * "Practical Object-oriented Design with UML"
 * Mark Priestley
 * McGraw-Hill (2004)
 */

package booksys.application.domain ;

import java.sql.Date ;
import java.sql.Time ;

//사전 예약 없이 식당에 들어가는 사람들을 나타내는 WalkIn클래스
public class WalkIn extends BookingImp
{
  public WalkIn(int c, Date d, Time t, Table tab)
  {
    super(c, d, t, tab) ;
  }


  //사전 예약 없이 식당에 들어가는 사람들에 관한 정보를 문자열로 반환한다.
  public String getDetails() {
    return "Walk-in (" + covers + ")" ;
  }
}
