import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SubjectTypeExtractor {

    // 과목 코드, 교양 유형 매핑 저장
    private static final Map<String, String> subjectTypeMap = new HashMap<>();

    static {
        // 기초교양
        subjectTypeMap.put("HALR1032", "기초교양");
        subjectTypeMap.put("HALR1050", "기초교양");
        subjectTypeMap.put("HALR1231", "기초교양");
        subjectTypeMap.put("HALR1238", "기초교양");
        subjectTypeMap.put("HALR1239", "기초교양");
        // 핵심교양
        subjectTypeMap.put("HALF9398", "전문지식탐구");
        subjectTypeMap.put("HALF9408", "전문지식탐구");
        subjectTypeMap.put("HALF9425", "전문지식탐구");
        subjectTypeMap.put("HALF9426", "전문지식탐구");
        subjectTypeMap.put("HALF9427", "창의적문제해결");
        subjectTypeMap.put("HALF9428", "창의적문제해결");
        subjectTypeMap.put("HALR1040", "창의적문제해결");
        subjectTypeMap.put("HALR1230", "창의적문제해결");
        subjectTypeMap.put("HALF9429", "융복합");
        subjectTypeMap.put("HALF9430", "융복합");
        subjectTypeMap.put("HALF9431", "융복합");
        subjectTypeMap.put("HALF9432", "융복합");
        subjectTypeMap.put("HALF9433", "다양성존중");
        subjectTypeMap.put("HALF9434", "다양성존중");
        subjectTypeMap.put("HALF9435", "다양성존중");
        subjectTypeMap.put("HALF9404", "윤리실천");
        subjectTypeMap.put("HALF9437", "윤리실천");
        subjectTypeMap.put("HALF9438", "윤리실천");
        subjectTypeMap.put("HALR1038", "윤리실천");
        // 균형교양 생략
    }

    public static void processJsonFile(String fileName) {
        try {
            String jsonString = readJsonFile(fileName);
            JSONArray jsonArray = new JSONArray(jsonString);

            // 각 과목에 대한 교양 유형 확인& JSON 데이터 추출
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String code = jsonObject.getString("code");
                String subjectType = subjectTypeMap.getOrDefault(code, null);
                jsonObject.put("subject_type", subjectType);
                // 핵심교양, 기초교양, 균형교양이 아닌 경우 null 처리
                if (subjectType == null) {
                    jsonObject.put("subject_type", JSONObject.NULL);
                }
                // 수정된 JSON 데이터 출력
                System.out.println(jsonObject.toString(2));
            }

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }

    // JSON 파일을 읽고 문자열로 반환
    private static String readJsonFile(String fileName) throws IOException {
        StringBuilder jsonContent = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                jsonContent.append(line);
            }
        }
        return jsonContent.toString();
    }
}
