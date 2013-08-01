package com.myetc_ui.main.othercomponentchild;

import java.util.ArrayList;
import java.util.List;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.Toast;

import com.myetc_ui.about.scanner.Scanner_Type;
import com.myetc_ui.about.scanner.Scanner_Type_Enum;
import com.myetc_ui.adapter.UserAdapter;
import com.myetc_ui.base.BaseActivity;
import com.myetc_ui.bean.NewSpinner;
import com.myetc_ui.R;

/**
 * 常用按钮，为了简单，简单再简单。代码已经小白的不能再小白了！ 无任何逻辑可言！完成流水代码！仅限此同级目录。
 * 
 * @author Administrator
 * 
 */
public class AutoCompleteTextViewActivity_u {
	private static final int name_id = R.string.othercomponent_autocompletetextview;

	@Scanner_Type(name = name_id, type = Scanner_Type_Enum.OTHERCOMPONENT)
	public static class AutoCompleteTextViewActivity extends BaseActivity {
		List<NewSpinner> list = new ArrayList<NewSpinner>();
		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			this.setContentView(R.layout.main_othercomponent_autocompletetextview);
			this.changeBar_Title(name_id);
			
			for (int i = 0; i < 20; i++) {
				list.add(new NewSpinner("你捴上大事了"+i,
						"据小道消息透露，你可能有事要发生了。喜还是不喜！来了就知道了", "今天", "小道网",
						9999));
			}
			test1();
			test2();
		}

		private void test2() {
			AutoCompleteTextView auto = (AutoCompleteTextView) findViewById(R.id.main_othercomponent_autocompletetextview_actv2);
			UserAdapter ua = new UserAdapter(this, list);
			auto.setAdapter(ua);
			
		}

		private void test1() {
			String[] books = new String[]  { "Afghanistan", "Albania", "Algeria",
		            "American Samoa", "Andorra", "Angola", "Anguilla", "Antarctica",
		            "Antigua and Barbuda", "Argentina", "Armenia", "Aruba",
		            "Australia", "Austria", "Azerbaijan", "Bahrain", "Bangladesh",
		            "Barbados", "Belarus", "Belgium", "Belize", "Benin", "Bermuda",
		            "Bhutan", "Bolivia", "Bosnia and Herzegovina", "Botswana",
		            "Bouvet Island", "Brazil", "British Indian Ocean Territory",
		            "British Virgin Islands", "Brunei", "Bulgaria", "Burkina Faso",
		            "Burundi", "Cote d'Ivoire", "Cambodia", "Cameroon", "Canada",
		            "Cape Verde", "Cayman Islands", "Central African Republic", "Chad",
		            "Chile", "China", "Christmas Island", "Cocos (Keeling) Islands",
		            "Colombia", "Comoros", "Congo", "Cook Islands", "Costa Rica",
		            "Croatia", "Cuba", "Cyprus", "Czech Republic",
		            "Democratic Republic of the Congo", "Denmark", "Djibouti",
		            "Dominica", "Dominican Republic", "East Timor", "Ecuador", "Egypt",
		            "El Salvador", "Equatorial Guinea", "Eritrea", "Estonia",
		            "Ethiopia", "Faeroe Islands", "Falkland Islands", "Fiji",
		            "Finland", "Former Yugoslav Republic of Macedonia", "France",
		            "French Guiana", "French Polynesia", "French Southern Territories",
		            "Gabon", "Georgia", "Germany", "Ghana", "Gibraltar", "Greece",
		            "Greenland", "Grenada", "Guadeloupe", "Guam", "Guatemala",
		            "Guinea", "Guinea-Bissau", "Guyana", "Haiti",
		            "Heard Island and McDonald Islands", "Honduras", "Hong Kong",
		            "Hungary", "Iceland", "India", "Indonesia", "Iran", "Iraq",
		            "Ireland", "Israel", "Italy", "Jamaica", "Japan", "Jordan",
		            "Kazakhstan", "Kenya", "Kiribati", "Kuwait", "Kyrgyzstan", "Laos",
		            "Latvia", "Lebanon", "Lesotho", "Liberia", "Libya",
		            "Liechtenstein", "Lithuania", "Luxembourg", "Macau", "Madagascar",
		            "Malawi", "Malaysia", "Maldives", "Mali", "Malta",
		            "Marshall Islands", "Martinique", "Mauritania", "Mauritius",
		            "Mayotte", "Mexico", "Micronesia", "Moldova" };

			// 创建一个ArrayAdapter封装数组
			ArrayAdapter<String> av = new ArrayAdapter<String>(this,
					android.R.layout.simple_dropdown_item_1line, books);
			AutoCompleteTextView auto = (AutoCompleteTextView) findViewById(R.id.main_othercomponent_autocompletetextview_actv1);
			auto.setAdapter(av);

		}

	}
}