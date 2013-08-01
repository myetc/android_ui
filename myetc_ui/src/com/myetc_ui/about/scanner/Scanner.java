package com.myetc_ui.about.scanner;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.util.Log;

import com.myetc_ui.about.ShareBean;

import dalvik.system.DexFile;
import dalvik.system.PathClassLoader;

public class Scanner {
	static List<MyMap> mmList = null;

	static class MyMap {
		Scanner_Type_Enum ste;
		List<ShareBean> listSB;

		public MyMap(Scanner_Type_Enum ste, List<ShareBean> listSB) {
			super();
			this.ste = ste;
			this.listSB = listSB;
		}

	}

	public Scanner(Context context, String package_start) {
		List<ShareBean> list = new ArrayList<ShareBean>();
		if (mmList == null) {
			System.out.println("=======第一次======调用扫描^^^^^^^^^^");

			mmList = new ArrayList<MyMap>();
			String path = "";
			try {
				path = context.getPackageManager().getApplicationInfo(
						context.getPackageName(), 0).sourceDir;
				DexFile dexfile_path = new DexFile(path);
				Enumeration<String> entries = dexfile_path.entries();
				while (entries.hasMoreElements()) {
					String name = (String) entries.nextElement();
					if (name.startsWith(package_start)) {
						Class<?> entryClass = context.getClassLoader()
								.loadClass(name);
						if (entryClass != null) {

							Scanner_Type st = entryClass
									.getAnnotation(Scanner_Type.class);
							if (st != null) {
								List<ShareBean> sbList = null;
								for (MyMap mm : mmList) {
									if (mm.ste == st.type()) {
										sbList = mm.listSB;
										sbList.add(new ShareBean(st.name(),
												entryClass));
										break;
									}
								}

								if (sbList == null) {
									sbList = new ArrayList<ShareBean>();
									sbList.add(new ShareBean(st.name(),
											entryClass));
									mmList.add(new MyMap(st.type(), sbList));
								}

							}
						}
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return;
	}

	public List<ShareBean> scan(Scanner_Type_Enum ste) {
		System.out.println("===============内存中取class信息");
		List<ShareBean> sbList = new ArrayList<ShareBean>();
		for (MyMap mm : mmList) {
			if (mm.ste == ste)
				return mm.listSB;
		}
		return null;
	}
}