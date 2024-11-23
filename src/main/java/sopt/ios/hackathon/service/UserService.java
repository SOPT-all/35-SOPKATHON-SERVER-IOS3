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
        return new GetDrinkResponse(user.getId(),user.getDrinkCnt(),calculateExceed(user.getDrinkCnt(),user.getLimitDrinkCnt(),user.getOverDrinkCnt()),user.getLimitDrinkCnt());
    }

    @Transactional
    public GetDrinkResponse patchDrinkStatus(final Long userId){
        User user = userRepository.findById(userId).orElseThrow(()-> new BusinessException(ErrorType.NOT_FOUND_MEMBER_ERROR));
        user.setDrinkCnt(user.getDrinkCnt()+1); // 소주잔 횟수 추가
        if(user.getOverDrinkCnt()==0&&user.getDrinkCnt()>user.getLimitDrinkCnt()){
            user.setOverDrinkCnt(user.getOverDrinkCnt()+1);
        }// 단지 주량이 넘었는지만 계산
        if(user.getOverDrinkCnt()>0&&user.getDrinkCnt()>user.getLimitDrinkCnt()+8*user.getOverDrinkCnt()){
            user.setOverDrinkCnt(user.getOverDrinkCnt()+1);
        }// 주량이 넘었고 추가로 마셨을 때 ( 1병 단위로 )
        return new GetDrinkResponse(user.getId(),user.getDrinkCnt(),calculateExceed(user.getDrinkCnt(),user.getLimitDrinkCnt(),user.getOverDrinkCnt()),user.getLimitDrinkCnt());
    }

    private boolean calculateExceed(int drinkCnt, int limitDrinkCnt,int overDrinkCnt){
        return drinkCnt >= limitDrinkCnt+8*overDrinkCnt;
    }

    public void removeUser(final Long userId){
        User user = userRepository.findById(userId).orElseThrow(()-> new BusinessException(ErrorType.NOT_FOUND_MEMBER_ERROR));
        userRepository.delete(user);
    }

}
