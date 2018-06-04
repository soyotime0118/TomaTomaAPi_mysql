package shop.park.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shop.park.model.Qna;
import shop.park.repository.QnaMapper;

@Service
public class QnaServiceImpl implements QnaService {
	
	@Autowired
	QnaMapper qnaMapper;

	@Override
	public List<Qna> selectAllQna() {
		List<Qna> qnaList = qnaMapper.selectAllQna();
		
		if(qnaList.isEmpty() || qnaList == null) {
			return null;
		} else {
			return qnaList;
		}
	}

	@Override
	public List<Qna> selectByQnaReply(String q_reply) {
		List<Qna> qnaReplyList = qnaMapper.selectByQnaReply(q_reply);
		
		if(qnaReplyList.isEmpty() || qnaReplyList == null) {
			return null;
		} else {
			return qnaReplyList;
		}
	}

	@Override
	public Qna selectByQnaNo(long q_no) {
		Qna qna = qnaMapper.selectByQnaNo(q_no);
		
		if(qna == null) {
			return null;
		} else {
			return qna;
		}
	}

	@Override
	public int insertQna(Qna qna) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateQna(Qna qna) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteQna(Qna qna) {
		// TODO Auto-generated method stub
		return 0;
	}

}
