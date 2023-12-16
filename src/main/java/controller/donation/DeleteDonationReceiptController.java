package controller.donation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import controller.Controller;
import model.DonationReceipt;
import model.service.DonationReceiptManager;
import model.service.UserNotFoundException;

public class DeleteDonationReceiptController implements Controller{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		 final Logger log = LoggerFactory.getLogger(DeleteDonationReceiptController.class);
		 try {
			 	DonationReceiptManager receipt_man = DonationReceiptManager.getInstance();

				int articleId = Integer.parseInt(request.getParameter("articleId"));
				DonationReceipt receipt = receipt_man.findByArticleId(articleId);
				int result = receipt_man.remove(articleId, receipt.getReceiptId());
				request.setAttribute("result", result);
				log.debug("Receipt Delete result: {}", result);
				
				String category = request.getParameter("category");
				
				return "redirect:/donationList/"+category+"?articleId="+articleId; // viewAnimalController로 리다이렉션
			
			}catch (ArithmeticException e) {
				// TODO: handle exception
				throw new UserNotFoundException("존재하지 않는 인증글입니다.");
			}
	}

}
