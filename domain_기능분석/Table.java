/*
 * Restaurant Booking System: example code to accompany
 *
 * "Practical Object-oriented Design with UML"
 * Mark Priestley
 * McGraw-Hill (2004)
 */

package booksys.application.domain ;



//레스토랑의 테이블을 표현하는 클래스이다.
public class Table
{
  private int number ;  //테이블의 고유 번호를 ing형으로 저장하고
  private int places ;  //테이블에서 사용 가능한 좌석수를 int형으로 저장한다.(private로 캡슐화 수행!!)
  
  public Table(int n, int p)
  {
    number = n ;
    places = p ;
  }

  public int getNumber()
  {
    return number ;
  }

  public int getPlaces()
  {
    return places ;
  }
}
