package com.sjwp.mission.domain.model.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
	
	@Autowired
	MemberRepository memberRepository;

	public Member save(Member member) {
		return memberRepository.save(member);
	}

    public void delete(Long memberId) {
    	memberRepository.deleteById(memberId);
    }

    public List<Member> findAll(){
    	return (List<Member>) memberRepository.findAll();
    }
}