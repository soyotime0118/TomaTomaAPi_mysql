package shop.park;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import shop.kim.login.service.LoginService;
import shop.park.model.Faq;
import shop.park.model.Notice;
import shop.park.model.Products;
import shop.park.model.Qna;
import shop.park.service.FaqServiceImpl;
import shop.park.service.NoticeServiceImpl;
import shop.park.service.ProductServiceImpl;
import shop.park.service.QnaServiceImpl;

@RestController
@RequestMapping(value = "/toma")
@CrossOrigin(origins="*")
public class MainController {
	
	@Autowired
	ProductServiceImpl productService;
	@Autowired
	NoticeServiceImpl noticeService;
	@Autowired
	FaqServiceImpl faqService;
	@Autowired
	QnaServiceImpl qnaService;
	@Autowired
	LoginService loginService;

	public static final Logger log = LoggerFactory.getLogger(MainController.class);
	
	// 상품 관련 controller
	@RequestMapping(value = "", method = RequestMethod.GET)
	public List<Products> listAllProducts() {
		log.info("Select All Products");
		
		List<Products> productsList = productService.selectAllProducts();
		
		return productsList;
	}
	
	@RequestMapping(value = "/product/{p_kind}", method = RequestMethod.GET)
	public List<Products> getByProductsKind(@PathVariable("p_kind") String p_kind) {
		log.info("Select Products by p_kind");
		
		List<Products> productsKindList = productService.selectByProductKind(p_kind);
		
		return productsKindList;
	}
	
	@RequestMapping(value = "/detail/product/{p_code}", method = RequestMethod.GET)
	public Products getByProductsCode(@PathVariable("p_code") String p_code) {
		log.info("Select Product by p_code");
		
		Products product = productService.selectByProductCode(p_code);
		
		return product;
	}
	
	@PostMapping(value = "/product")
	public ResponseEntity<?> insertProduct(@RequestBody Products product, UriComponentsBuilder ucBuilder) {
		log.info("Insert Product : " + product);
		
		productService.insertProduct(product);
		
		return new ResponseEntity<Products>(HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/product/{p_code}")
	public ResponseEntity<?> updateProduct(@PathVariable("p_code") String p_code, @RequestBody Products product) {
		log.info("Updating Product with p_code : " + product);
		
		productService.updateProduct(product);
		
		return new ResponseEntity<Products>(HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/product/{p_code}")
	public ResponseEntity<?> deleteProduct(@PathVariable("p_code") String p_code) {
		log.info("Deleting Product with p_code : " + p_code);
		
		productService.deleteProduct(p_code);
		
		return new ResponseEntity<Products>(HttpStatus.NO_CONTENT);
	}
	
	// 공지사항 관련 controller
	@RequestMapping(value = "/notice", method = RequestMethod.GET)
	public List<Notice> listAllNotices() {
		log.info("Select All Notices");
		
		List<Notice> noticeList = noticeService.selectAllNotice();
		
		return noticeList;
	}
	
	@RequestMapping(value = "/notice/{n_category}", method = RequestMethod.GET)
	public List<Notice> getByNoticeCategory(@PathVariable("n_category") String n_category) {
		log.info("Select Notice by n_category");
		
		List<Notice> noticeCategoryList = noticeService.selectByNoticeCategory(n_category);
		
		return noticeCategoryList;
	}
	
//	@RequestMapping(value = "/detail/notice/{n_no}", method = RequestMethod.GET)
//	public Notice getByNoticeNo(@PathVariable("n_no") long n_no) {
//		log.info("Select Notice by n_no");
//		
//		Notice notice = noticeService.selectByNoticeNo(n_no);
//		noticeService.incrementNoticeHits(n_no);
//		
//		return notice;
//	}
	
	@PutMapping(value = "/detail/notice/{n_no}")
	public ResponseEntity<?> incrementNoticeHits(@PathVariable("n_no") long n_no, @RequestBody Notice notice) {
		log.info("Increment Notice by n_no" + n_no);
//		Notice noticeObject = noticeService.selectByNoticeNo(n_no);
//		if(noticeObject == null) {
//			return new ResponseEntity<Notice>(HttpStatus.FAILED_DEPENDENCY);
//		}
		
		notice.setN_no(n_no);
		noticeService.incrementNoticeHits(n_no);
		
		return new ResponseEntity<Notice>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/notice/search/{n_title}", method = RequestMethod.GET)
	public List<Notice> getByNoticeTitleSearch(@PathVariable("n_title") String n_title) {
		log.info("Select Notice By n_title");
		
		List<Notice> searchNoticeTitle = noticeService.searchByNoticeTitle(n_title);
		
		return searchNoticeTitle;
		
	}
	
	// 자주 묻는 질문 관련 controller
	@RequestMapping(value = "/faq", method = RequestMethod.GET)
	public List<Faq> listAllFaq() {
		log.info("Select All Faq");
		
		List<Faq> faqList = faqService.selectAllFaq();
		
		return faqList;
	}
	
	@RequestMapping(value = "/faq/{f_category}", method = RequestMethod.GET)
	public List<Faq> getByFaqCategory(@PathVariable("f_category") String f_category) {
		log.info("Select Faq by f_category");
		
		List<Faq> faqCategoryList = faqService.selectByFaqCategory(f_category);
		
		return faqCategoryList;
	}
	
//	@RequestMapping(value = "/detail/faq/{f_no}", method = RequestMethod.GET)
//	public Faq getByFaqNo(@PathVariable("f_no") long f_no) {
//		log.info("Select Faq by f_no");
//		
//		Faq faq = faqService.selectByFaqNo(f_no);
//		faqService.incrementFaqHits(f_no);
//		
//		return faq;
//	}
	
	@PutMapping(value = "/detail/faq/{f_no}")
	public ResponseEntity<?> incrementFaqHits(@PathVariable("f_no") long f_no, @RequestBody Faq faq) {
		log.info("Increment Faq by f_no" + f_no);
		
		faq.setF_no(f_no);
		faqService.incrementFaqHits(f_no);
		
		return new ResponseEntity<Faq>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/faq/search/{f_title}", method = RequestMethod.GET)
	public List<Faq> getByFaqTitleSearch(@PathVariable("f_title") String f_title) {
		log.info("Select Faq By f_title");
		
		List<Faq> searchFaqTitle = faqService.searchByFaqTitle(f_title);
		
		return searchFaqTitle;
	}
	
	// 1:1 질문 관련 controller
	@RequestMapping(value = "/qna/list/{u_id}", method = RequestMethod.GET)
	public List<Qna> listAllQna(@PathVariable("u_id") String u_id) {
		log.info("Select All Qna");
		
		List<Qna> qnaList = qnaService.selectAllQna(u_id);
		
		return qnaList;
	}
	
	@RequestMapping(value = "/detail/qna/{q_no}", method = RequestMethod.GET)
	public Qna getByQnaNo(@PathVariable("q_no") long q_no) {
		log.info("Select Qna by q_no");
		
		Qna qna = qnaService.selectByQnaNo(q_no);
		
		return qna;
	}
	
	@RequestMapping(value = "/qna/search/{q_title}", method = RequestMethod.GET)
	public List<Qna> getByQnaTitleSearch(@PathVariable("q_title") String q_title) {
		log.info("Select Qna By f_title");
		
		List<Qna> searchQnaTitle = qnaService.searchByQnaTitle(q_title);
		
		return searchQnaTitle;
	}
	
	@RequestMapping(value = "/qna/{q_reply}", method = RequestMethod.GET)
	public List<Qna> getByQnaReply(@PathVariable("q_reply") String q_reply) {
		log.info("Select Qna by q_reply");
		
		List<Qna> qnaReplyList = qnaService.selectByQnaReply(q_reply);
		
		return qnaReplyList;
	}
	
	@PostMapping(value = "/qna/write")
	public ResponseEntity<?> insertQna(@RequestBody Qna qna, UriComponentsBuilder ucBuilder) {
		log.info("Insert Qna : " + qna);
		
		qnaService.insertQna(qna);
		
		return new ResponseEntity<Qna>(HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/qna/update/{q_no}")
	public ResponseEntity<?> updateQna(@PathVariable("q_no") long q_no, @RequestBody Qna qna) {
		log.info("Updating Qna with p_code : " + q_no);
		
		Qna qnaObject = qnaService.selectByQnaNo(q_no);
		if(qnaObject == null) {
			return new ResponseEntity<Qna>(HttpStatus.FAILED_DEPENDENCY);
		}
		
		qna.setQ_no(q_no);
		
		qnaService.updateQna(qna);
		
		return new ResponseEntity<Qna>(HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/qna/{q_no}")
	public ResponseEntity<?> deleteQna(@PathVariable("q_no") long q_no) {
		log.info("Deleting Qna with p_code : " + q_no);
		
		qnaService.deleteQna(q_no);
		
		return new ResponseEntity<Qna>(HttpStatus.NO_CONTENT);
	}
	
}
