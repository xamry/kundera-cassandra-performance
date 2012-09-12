package com.impetus.kunderaperf.dto;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "admins",schema = "KunderaExamples@twingo")
public class AdminDO {

        /**
         *
         */
        private static final long serialVersionUID = 8016810898715793315L;

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(name = "_id")
        private String id;

        @Column(name = "username")
        private String userName;

        @Column(name = "password")
        private String password;

        @Column(name = "admin_type")
        private int adminType;

        @Column(name = "email_id")
        private String emailId;

        @Column(name = "access")
        private String access;


        /**
         * @return the id
         */
        public String getId() {
                return id;
        }

        /**
         * @param id the id to set
         */
        public void setId(String id) {
                this.id = id;
        }

        /**
         * @return the userName
         */
        public String getUserName() {
                return userName;
        }

        /**
         * @param userName
         *            the userName to set
         */
        public void setUserName(String userName) {
                this.userName = userName;
        }

        /**
         * @return the password
         */
        public String getPassword() {
                return password;
        }

        /**
         * @param password
         *            the password to set
         */
        public void setPassword(String password) {
                this.password = password;
        }

        /**
         * @return the adminType
         */
        public int getAdminType() {
                return adminType;
        }

        /**
         * @param adminType
         *            the adminType to set
         */
        public void setAdminType(int adminType) {
                this.adminType = adminType;
        }

        /**
         * @return the emailId
         */
        public String getEmailId() {
                return emailId;
        }

        /**
         * @param emailId
         *            the emailId to set
         */
        public void setEmailId(String emailId) {
                this.emailId = emailId;
        }

        /**
         * @return the access
         */
        public String getAccess() {
                return access;
        }

        /**
         * @param access
         *            the access to set
         */
        public void setAccess(String access) {
                this.access = access;
        }

}
