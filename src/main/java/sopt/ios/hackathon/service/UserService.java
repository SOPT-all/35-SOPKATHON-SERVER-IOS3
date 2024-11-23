package sopt.ios.hackathon.service;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import sopt.ios.hackathon.Repository.UserRepository;
import sopt.ios.hackathon.global.exception.BusinessException;
import sopt.ios.hackathon.global.exception.ErrorType;
import sopt.ios.hackathon.model.dto.GetDrinkResponse;
import sopt.ios.hackathon.model.entity.User;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    public GetDrinkResponse fetchDrinkStatus(final Long userId){
        User user = userRepository.findById(userId).orElseThrow(()-> new BusinessException(ErrorType.NOT_FOUND_MEMBER_ERROR));
        return new GetDrinkResponse(user.getId(),user.getDrinkCnt(),calculateExceed(user.getDrinkCnt(),user.getLimitDrinkCnt(),user.getOverDrinkCnt()),user.getOverDrinkCnt());
    }

    @Transactional
    public GetDrinkResponse patchDrinkStatus(final Long userId){
        User user = userRepository.findById(userId).orElseThrow(()-> new BusinessException(ErrorType.NOT_FOUND_MEMBER_ERROR));
        user.setDrinkCnt(user.getDrinkCnt()+1);
        return new GetDrinkResponse(user.getId(),user.getDrinkCnt(),calculateExceed(user.getDrinkCnt(),user.getLimitDrinkCnt(),user.getOverDrinkCnt()),user.getOverDrinkCnt());
    }

    private boolean calculateExceed(int getDrinkCnt, int getLimitDrinkCnt,int overDrinkCnt){
        return getDrinkCnt >= getLimitDrinkCnt+8*overDrinkCnt;
    }

}
