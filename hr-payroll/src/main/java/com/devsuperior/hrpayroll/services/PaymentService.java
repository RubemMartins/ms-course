package com.devsuperior.hrpayroll.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.devsuperior.hrpayroll.entites.Payment;
import com.devsuperior.hrpayroll.entites.Worker;
import com.devsuperior.hrpayroll.feignclients.WorkerFeignClient;

@Service
public class PaymentService {
	
	@Value("${hr-worker.host}")
	private String workerHost;
	
//	@Autowired
//	private RestTemplate restTemplate;
	
	@Autowired
	private WorkerFeignClient workerFeignClient;

	public Payment getPayment(long workerId, int days) {
		
//		Map<String, String> uriVariables = new HashMap<>();
//		uriVariables.put("id",""+workerId);
		
		Worker worker = workerFeignClient.findById(workerId).getBody();
		return new Payment(worker.getName(),worker.getDailyIncome(),days);
	}
}
