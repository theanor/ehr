package be.monolith.ehr.it;

import org.springframework.cloud.openfeign.FeignClient;

import be.monolith.ehr.iam.IamService;
import be.monolith.ehr.iam.IamServiceConstants;

@FeignClient(IamServiceConstants.SERVICE_ID)
public interface IamServiceClient extends IamService {

}
