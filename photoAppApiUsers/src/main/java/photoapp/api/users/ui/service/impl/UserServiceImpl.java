package photoapp.api.users.ui.service.impl;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import photoapp.api.users.ui.controllers.UsersController;
import photoapp.api.users.ui.data.UserEntity;
import photoapp.api.users.ui.dto.UserDto;
import photoapp.api.users.ui.exceptions.UsersNotFoundException;
import photoapp.api.users.ui.repository.UserRepository;
import photoapp.api.users.ui.service.UserService;
import photoapp.api.users.utils.Converter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    private final Converter<UserEntity> userEntityConverter = new Converter<>();
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final Logger LOG = LoggerFactory.getLogger(UsersController.class);

    @Autowired
    public  UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public UserDto create(UserDto userDto) {
        userDto.setUserId(UUID.randomUUID());
        userDto.setEncryptPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        UserEntity userEntity = modelMapper.map(userDto, UserEntity.class);
        userRepository.save(userEntity);
        LOG.info("\n" + userEntityConverter.json(userEntity));
        UserDto returnValue = modelMapper.map(userEntity, UserDto.class);
        return returnValue;
    }

    @Override
    public Iterable<UserEntity> findAll() throws UsersNotFoundException {
        List<UserEntity> userEntities = (List<UserEntity>) userRepository.findAll();
        if (userEntities.isEmpty()) {
            throw new UsersNotFoundException();
        }
        userEntities.forEach(userEntity -> LOG.info(userEntityConverter.json(userEntity)));
        return userEntities;
    }

    @Override
    public UserEntity getUser(UUID id) {
        return null;
    }

    @Override
    public UserEntity update(UserDto userDto) {
        return null;
    }

    @Override
    public boolean delete(UUID id) {
        return false;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByEmail(email);
        if (userEntity == null) {
            throw new UsernameNotFoundException(email);
        }
        return new User(
                userEntity.getEmail(),
                userEntity.getEncryptPassword(),
                new ArrayList<>()
        );
    }

    @Override
    public UserDto getUserDetailsByEmail(String email) {
        UserEntity userEntity =userRepository.findByEmail(email);
        if (userEntity == null) {
            throw new UsernameNotFoundException(email);
        }
        return new ModelMapper().map(userEntity, UserDto.class);
    }
}
