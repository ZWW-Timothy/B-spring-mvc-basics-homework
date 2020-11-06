package com.thoughtworks.capacity.gtb.mvc.service;

import javax.validation.GroupSequence;

@GroupSequence({UserNameBlankCheck.class, UserNameInvalidCheck.class, UserPasswordBlankCheck.class, UserPasswordInvalidCheck.class, UserEmailCheck.class})
public interface UserCheckSequence {
}
