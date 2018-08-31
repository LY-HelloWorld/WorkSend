package com.a51zhipaiwang.worksend.Utils;

public class IdCoverText {

    /**
     * 姓名转换
     * @param sex
     * @return
     */
    public static String coverSex(String sex){
        if (sex == null){
            return "";
        }
        switch (sex) {
            case "010":
                return "男";
            case "020":
                return "女";
            case "030":
                return "不限";
			case "男":
				return "010";
			case "女":
				return "020";
			case "不限":
				return "030";
            default:
                return "";
        }
    }



    /**
     * 工作状态转换
     * @param workStatus
     * @return
     */
    public static String coverWorkStatus(String workStatus){
        if (workStatus == null){
            return "";
        }
        switch (workStatus) {
            case "010":
                return "离职";
            case "020":
                return "在职";
			case "030":
				return "不限";
			case "离职":
				return "010";
			case "在职":
				return "020";
			case "不限":
				return "030";
            default:
                return "";
        }
    }

    /**
     * 工作经历
     * @param workExprence
     * @return
     */
    public static String coverWorkExprence(String workExprence){
        if (workExprence == null){
            return "";
        }
        switch (workExprence) {
            case "010":
                return "不限";
            case "020":
                return "应届生";
            case "030":
                return "1-3年";
            case "040":
                return "3-5年";
            case "050":
                return "5-10年";
            case "060":
                return "10年以上";

			case "不限":
				return "010";
			case "应届生":
				return "020";
			case "1-3年":
				return "030";
			case "3-5年":
				return "040";
			case "5-10年":
				return "050";
			case "10年以上":
				return "060";
            default:
                return "";
        }
    }


    /**
     * 教育经历
     * @param education
     * @return
     */
    public static String coverEducation(String education){
        if (education == null){
            return "";
        }
        switch (education) {
            case "010":
                return "不限";
            case "020":
                return "高中或以下";
            case "030":
                return "中专";
            case "040":
                return "大专";
            case "050":
                return "本科";
            case "060":
                return "硕士及以上";

			case "不限":
				return "010";
			case "高中或以下":
				return "020";
			case "中专":
				return "030";
			case "大专":
				return "040";
			case "本科":
				return "050";
			case "硕士及以上":
				return "060";
            default:
                return "";
        }

    }

    /**
     * 工资
     * @param money
     * @return
     */
    public static String coverMoney(String money){
        if (money == null){
            return "";
        }
        switch (money) {
            case "010":
                return "不限";
            case "020":
                return "3k-5k";
            case "030":
                return "5k-10k";
            case "040":
                return "10-15k";
            case "050":
                return "15-20k";
            case "060":
                return "20k以上";
			case "不限":
				return "010";
			case "3k-5k":
				return "020";
			case "5k-10k":
				return "030";
			case "10-15k":
				return "040";
			case "15-20k":
				return "050";
			case "20k以上":
				return "060";
            default:
                return "";
        }
    }

	/**
     * 企业认证状态
     * @param authentication
     * @return
     */
    public static String authentication(String authentication){
        if (authentication == null){
            return "";
        }
        switch (authentication) {
            case "010":
                return "已认证";
            case "020":
                return "未认证";
			case "已认证":
				return "010";
			case "未认证":
				return "020";
            default:
                return "";
        }
    }

	/**
		 * 是否开通会员
		 * @param vip
		 * @return
		 */
		public static String vip(String vip){
			if (vip == null){
				return "";
			}
			switch (vip) {
				case "010":
					return "已开通";
				case "020":
					return "未开通";
				case "已开通":
					return "010";
				case "未开通":
					return "020";
				default:
					return "";
			}
		}

		/**
		 * 匹配简历状态
		 * @param distributeSalary
		 * @return
		 */
		public static String distributeSalary(String distributeSalary){
			if (distributeSalary == null){
				return "";
			}
			switch (distributeSalary) {
				case "010":
					return "未匹配";
				case "020":
					return "匹配成功";
				case "未匹配":
					return "010";
				case "匹配成功":
					return "020";
				default:
					return "";
			}
		}

		/**
		 * 新消息
		 * @param newMessage
		 * @return
		 */
		public static String newMessage(String newMessage){
			if (newMessage == null){
				return "";
			}
			switch (newMessage) {
				case "010":
					return "未查看";
				case "020":
					return "已查看";
				case "未查看":
					return "010";
				case "已查看":
					return "020";
				default:
					return "";
			}
		}

		/**
		 * 企业消息类型
		 * @param enterpriseMessageState
		 * @return
		 */
		public static String enterpriseMessageState(String enterpriseMessageState){
			if (enterpriseMessageState == null){
				return "";
			}
			switch (enterpriseMessageState) {
				case "010":
					return "通知消息";
				case "020":
					return "职派活动";
				case "030":
					return "派单成功反馈";
				case "040":
					return "已接收试岗";
				case "通知消息":
					return "010";
				case "职派活动":
					return "020";
				case "派单成功反馈":
					return "030";
				case "已接收试岗":
					return "040";
				default:
					return "";
			}
		}

		/**
		 * 抢单状态
		 * @param snatchingState
		 * @return
		 */
		public static String snatchingState(String snatchingState){
			if (snatchingState == null){
				return "";
			}
			switch (snatchingState) {
				case "010":
					return "已抢单";
				case "020":
					return "未抢单";
				case "已抢单":
					return "010";
				case "未抢单":
					return "020";
				default:
					return "";
			}
		}

		/**
		 * 试岗状态
		 * @param testPostState
		 * @return
		 */
		public static String testPostState(String testPostState){
			if (testPostState == null){
				return "";
			}
			switch (testPostState) {
				case "010":
					return "未接收试岗";
				case "020":
					return "已接受试岗";
				case "未接收试岗":
					return "010";
				case "已接受试岗":
					return "020";
				default:
					return "";
			}
		}

		/**
		 * 用户消息状态
		 * @param userMessageState
		 * @return
		 */
		public static String userMessageState(String userMessageState){
			if (userMessageState == null){
				return "";
			}
			switch (userMessageState) {
				case "010":
					return "HR派单消息";
				case "020":
					return "投递反馈";
				case "030":
					return "试岗通知";
				case "040":
					return "系统消息";
				case "HR派单消息":
					return "010";
				case "投递反馈":
					return "020";
				case "试岗通知":
					return "030";
				case "系统消息":
					return "040";
				default:
					return "";
			}
		}

		/**
		 * 消息类别(这个你应该用不到)
		 * @param col1
		 * @return
		 */
		public static String col1(String col1){
			if (col1 == null){
				return "";
			}
			switch (col1) {
				case "010":
					return "抢单类别";
				case "020":
					return "试岗类别";
				case "抢单类别":
					return "010";
				case "试岗类别":
					return "020";
				default:
					return "";
			}
		}

		public static String coverWorkType(String workType){
			if (workType == null){
				return "";
			}
			switch (workType) {
				case "020":
					return "兼职";
				case "010":
					return "全职";
				case "兼职":
					return "020";
				case "全职":
					return "010";
				default:
					return "";
			}
		}

}
