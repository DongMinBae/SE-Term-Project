/*
 * Restaurant Booking System: example code to accompany
 *
 * "Practical Object-oriented Design with UML"
 * Mark Priestley
 * McGraw-Hill (2004)
 */

package booksys.application.domain ;

//관리자가 예약을 실시간으로 모니터링하고 상호 작용하는데 사용할 수 있는 메서드를 선언한 인터페이스
public interface BookingObserver
{
  
  public void update() ;

  public boolean message(String s, boolean confirm) ;
}
