package jpa;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Locker {

	@Id @GeneratedValue
	private Long id;

	//양방향 관계
	@OneToOne(mappedBy = "locker")
	private Member member;

	private String name;

}
