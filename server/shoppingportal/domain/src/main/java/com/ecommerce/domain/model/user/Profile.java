package com.ecommerce.domain.model.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Profile {

        private Info info;

        private Email email;

        public Profile(Info info, Email email) {
                this.info = info;
                this.email = email;
        }

        public Profile() {

        }
}
