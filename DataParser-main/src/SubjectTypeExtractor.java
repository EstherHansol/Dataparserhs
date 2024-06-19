import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SubjectTypeExtractor {

    // 교양 유형 맵
    private static final Map<String, Map<String, String>> subjectTypeMap = new HashMap<>();

    static {
        // 핵심교양
        Map<String, String> coreGenEd = new HashMap<>();
        coreGenEd.put("HBLB5056", "expertiseResearchSkill");
        coreGenEd.put("HBLB5070", "expertiseResearchSkill");
        coreGenEd.put("HBLB5071", "expertiseResearchSkill");
        coreGenEd.put("HBLB5072", "expertiseResearchSkill");
        coreGenEd.put("HBLB5060", "creativeProblemSolvingSkill");
        coreGenEd.put("HBLB5031", "creativeProblemSolvingSkill");
        coreGenEd.put("HBLB5075", "creativeProblemSolvingSkill");
        coreGenEd.put("HBLB5076", "creativeProblemSolvingSkill");
        coreGenEd.put("HBLD5009", "interdisciplinarySkill");
        coreGenEd.put("HBLD0051", "interdisciplinarySkill");
        coreGenEd.put("HBLA5052", "interdisciplinarySkill");
        coreGenEd.put("HBLB5080", "interdisciplinarySkill");
        coreGenEd.put("HBLA5060", "diversityRespectSkill");
        coreGenEd.put("HBLB5058", "diversityRespectSkill");
        coreGenEd.put("HBLA0272", "diversityRespectSkill");
        coreGenEd.put("HBLB5085", "diversityRespectSkill");
        coreGenEd.put("HBLA5061", "ethicalPracticeSkill");
        coreGenEd.put("HBLA5063", "ethicalPracticeSkill");
        coreGenEd.put("HBLG2017", "ethicalPracticeSkill");
        coreGenEd.put("HBLB5090", "ethicalPracticeSkill");

        subjectTypeMap.put("핵심교양", coreGenEd);

        // 기초교양
        Map<String, String> basicGenEd = new HashMap<>();
        basicGenEd.put("HBLA5001", "thinkingAndExpression");
        basicGenEd.put("HBLA5015", "EFAP");
        basicGenEd.put("HBLA5200", "basicMath");
        basicGenEd.put("HBLA5201", "computingdata"); //변수명 추가
        basicGenEd.put("HBLA5002", "algorithmgamecontents"); //변수명 추가
        //교양과 인성 아직 추가전임

        subjectTypeMap.put("기초교양", basicGenEd);

        // 균형교양
        Map<String, String> balanceGenEd = new HashMap<>();
        balanceGenEd.put("HBLR5003", "humanity");
        balanceGenEd.put("HBLA5021", "humanity");
        balanceGenEd.put("HBLA0364", "humanity");
        balanceGenEd.put("HBLA5004", "humanity");
        balanceGenEd.put("HBLG1007", "humanity");
        balanceGenEd.put("HBLA5005", "humanity");
        balanceGenEd.put("HBLA5022", "humanity");
        balanceGenEd.put("HBLA5008", "humanity");
        balanceGenEd.put("HBLG9040", "humanity");
        balanceGenEd.put("HBLA5204", "humanity");
        balanceGenEd.put("HBLA5059", "humanity");
        balanceGenEd.put("HBLA5053", "humanity");
        balanceGenEd.put("HBLA0340", "humanity");
        balanceGenEd.put("HBLA0343", "humanity");


        balanceGenEd.put("HBLB1036", "socialScience");
        balanceGenEd.put("HBLG1009", "socialScience");
        balanceGenEd.put("HBLG9042", "socialScience");
        balanceGenEd.put("HBLG2015", "socialScience");
        balanceGenEd.put("HBLB5032", "socialScience");
        balanceGenEd.put("HBLB5002", "socialScience");
        balanceGenEd.put("HBLA5035", "socialScience");
        balanceGenEd.put("HBLB5093", "socialScience");

        balanceGenEd.put("HBLB1025", "art");
        balanceGenEd.put("HBLB5020", "art");
        balanceGenEd.put("HBLD0081", "art");
        balanceGenEd.put("HBLD0072", "art");
        balanceGenEd.put("HBLD5034", "art");
        balanceGenEd.put("HBLD1020", "art");
        balanceGenEd.put("HBLD5023", "art");
        balanceGenEd.put("HBLD5041", "art");
        balanceGenEd.put("HBLD5040", "art");
        balanceGenEd.put("HBLD5042", "art");
        balanceGenEd.put("HBLD5055", "art");
        balanceGenEd.put("HBLD5056", "art");

        balanceGenEd.put("HBLC5003", "natureandengineering"); //자연굥학 따로 안 있음 (합쳐져 있음)
        balanceGenEd.put("HBLC5046", "natureandengineering");
        balanceGenEd.put("HBLB5061", "natureandengineering");
        balanceGenEd.put("HBLC5048", "natureandengineering");
        balanceGenEd.put("HBLC5045", "natureandengineering");
        balanceGenEd.put("HBLA5206", "natureandengineering");
        balanceGenEd.put("HBLG2021", "natureandengineering");
        balanceGenEd.put("HBLA5070", "natureandengineering");
        balanceGenEd.put("HBLA5071", "natureandengineering");
        balanceGenEd.put("HBLC1003", "natureandengineering");
        balanceGenEd.put("HBLC5044", "natureandengineering");
        balanceGenEd.put("HBLB5059", "natureandengineering");
        balanceGenEd.put("HBLC5049", "natureandengineering");
        balanceGenEd.put("HBLF7815", "natureandengineering");
        balanceGenEd.put("HBLF7816", "natureandengineering");
        balanceGenEd.put("HBLC5061", "natureandengineering");

        balanceGenEd.put("HBLD5060", "bridge");
        balanceGenEd.put("HBLD5061", "bridge");
        balanceGenEd.put("HBLD5062", "bridge");
        balanceGenEd.put("HBLD5063", "bridge");
        balanceGenEd.put("HBLD5064", "bridge");
        balanceGenEd.put("HBLD5065", "bridge");
        balanceGenEd.put("HBLA5041", "bridge");
        balanceGenEd.put("HBLA5065", "bridge");

        subjectTypeMap.put("균형교양", balanceGenEd);

        // 일반선택 (normal)
        Map<String, String> normalGenEd = new HashMap<>();
        normalGenEd.put("HBLG9043", "normal");
        normalGenEd.put("HBLG9047", "normal");
        normalGenEd.put("HBLG2046", "normal");
        normalGenEd.put("HBLA5034", "normal");
        normalGenEd.put("HBLA5062", "normal");
        normalGenEd.put("HBLE5021", "normal");

        normalGenEd.put("HBLG1006", "normal");
        normalGenEd.put("HBLA0342", "normal");
        normalGenEd.put("HBLA0141", "normal");
        normalGenEd.put("HBLA5023", "normal");
        normalGenEd.put("HBLA5011", "normal");
        normalGenEd.put("HBLA5009", "normal");

        normalGenEd.put("HBLD5050", "normal");
        normalGenEd.put("HBLG2034", "normal");
        normalGenEd.put("HBLG2045", "normal");
        normalGenEd.put("HBLG2033", "normal");
        normalGenEd.put("HBLE5022", "normal");
        normalGenEd.put("HBLE5026", "normal");

        normalGenEd.put("HBLA0241", "normal");
        normalGenEd.put("HBLR5009", "normal");
        normalGenEd.put("HBLR5011", "normal");
        normalGenEd.put("HBLA0092", "normal");
        normalGenEd.put("HBLA0321", "normal");
        normalGenEd.put("HBLA0072", "normal");
        normalGenEd.put("HBLA0052", "normal");
        normalGenEd.put("HBLA5064", "normal");
        normalGenEd.put("HBLA5027", "normal");
        normalGenEd.put("HBLA0061", "normal");
        normalGenEd.put("HBLA0051", "normal");
        normalGenEd.put("HBLA0171", "normal");
        normalGenEd.put("HBLA0322", "normal");
        normalGenEd.put("HBLR5004", "normal");
        normalGenEd.put("HBLR5016", "normal");
        normalGenEd.put("HBLR5030", "normal");
        normalGenEd.put("HBLR5031", "normal");
        normalGenEd.put("HBLA0161", "normal");
        normalGenEd.put("HBLR5008", "normal");
        normalGenEd.put("HBLA5031", "normal");
        normalGenEd.put("HBLR5032", "normal");
        normalGenEd.put("HBLR2010", "normal");
        normalGenEd.put("HBLR2020", "normal");
        normalGenEd.put("HBLG2042", "normal");
        normalGenEd.put("HBLR2030", "normal");
        normalGenEd.put("HBLR2040", "normal");
        normalGenEd.put("HBLR2060", "normal");
        normalGenEd.put("HBLG2040", "normal");
        normalGenEd.put("HBLG2038", "normal");
        normalGenEd.put("HBLA5025", "normal");
        normalGenEd.put("HBLA5026", "normal");
        normalGenEd.put("HBLG2035", "normal");
        normalGenEd.put("HBLR5026", "normal");
        normalGenEd.put("HBLF7841", "normal");

        normalGenEd.put("HBLD5051", "normal");
        normalGenEd.put("HBLC5010", "normal");
        normalGenEd.put("HBLB0011", "normal");
        normalGenEd.put("HBLB5062", "normal");
        normalGenEd.put("HBLG2013", "normal");
        normalGenEd.put("HBLA0323", "normal");
        normalGenEd.put("HBLB5055", "normal");
        normalGenEd.put("HBLB5064", "normal");
        normalGenEd.put("HBLA5056", "normal");
        normalGenEd.put("HBLA5068", "normal");
        normalGenEd.put("HBLA5069", "normal");
        normalGenEd.put("HBLF7813", "normal");
        normalGenEd.put("HBLA5205", "normal");
        normalGenEd.put("HBLA0332", "normal");
        normalGenEd.put("BLLB5091", "normal");

        normalGenEd.put("HBLA5073", "normal");
        normalGenEd.put("HBLC5004", "normal");
        normalGenEd.put("HBLD5004", "normal");
        normalGenEd.put("HBLA5207", "normal");
        normalGenEd.put("HBLA5203", "normal");

        normalGenEd.put("HBLG2016", "normal");
        normalGenEd.put("HBLE5024", "normal");
        normalGenEd.put("HBLC5009", "normal");
        normalGenEd.put("HBLE5020", "normal");
        normalGenEd.put("HBLA5209", "normal");
        normalGenEd.put("HBLB5066", "normal");
        normalGenEd.put("HBLC5060", "normal");
        normalGenEd.put("HBLC5062", "normal");

        normalGenEd.put("HBLD5032", "normal");
        normalGenEd.put("HBLG2014", "normal");
        normalGenEd.put("HBLB5013", "normal");
        normalGenEd.put("HBLD5013", "normal");
        normalGenEd.put("HBLD5012", "normal");
        normalGenEd.put("HBLB5014", "normal");
        normalGenEd.put("HBLD5035", "normal");
        normalGenEd.put("HBLD5024", "normal");
        normalGenEd.put("HBLD1014", "normal");
        normalGenEd.put("HBLD5025", "normal");
        normalGenEd.put("HBLD0112", "normal");
        normalGenEd.put("HBLA5055", "normal");
        normalGenEd.put("HBLD5049", "normal");
        normalGenEd.put("HBLD5036", "normal");

        normalGenEd.put("HBLD1023", "normal");
        normalGenEd.put("HBLR1070", "normal");
        normalGenEd.put("HBLG2020", "normal");
        normalGenEd.put("HBLD5046", "normal");
        normalGenEd.put("HBLD5047", "normal");
        normalGenEd.put("HBLD5029", "normal");
        normalGenEd.put("HBLD5045", "normal");
        normalGenEd.put("HBLD5037", "normal");
        normalGenEd.put("HBLD5054", "normal");
        normalGenEd.put("HBLB5065", "normal");
        normalGenEd.put("HBLD5057", "normal");
        normalGenEd.put("HBLD1021", "normal");

        normalGenEd.put("HBLF0008", "normal");
        normalGenEd.put("HBLF7807", "normal");
        normalGenEd.put("HBLA5044", "normal");
        normalGenEd.put("HBLG9058", "normal");
        normalGenEd.put("HBLA5048", "normal");
        normalGenEd.put("HBLA5045", "normal");
        //사회봉사 단대별 상이 .. 아직 추가 전
        normalGenEd.put("HBLC5057", "normal");
        normalGenEd.put("HBLB5037", "normal");
        normalGenEd.put("HBLB5031", "normal");
        normalGenEd.put("HBLC5050", "normal");
        normalGenEd.put("HBLA5054", "normal");
        normalGenEd.put("HBLG2048", "normal");

        normalGenEd.put("HBLF0013", "normal");
        normalGenEd.put("HBLF0018", "normal");
        normalGenEd.put("HBLF0002", "normal");
        normalGenEd.put("HBLF0017", "normal");
        normalGenEd.put("HBLF0024", "normal");
        normalGenEd.put("HBLF0025", "normal");
        normalGenEd.put("HBLF0027", "normal");
        normalGenEd.put("HBLF0026", "normal");
        normalGenEd.put("HBLF7809", "normal");
        normalGenEd.put("HBLF7811", "normal");
        normalGenEd.put("HBLF7810", "normal");


        subjectTypeMap.put("일반선택", normalGenEd);
    }

    public static void main(String[] args) {
        String inputFilePath = "input.json";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(inputFilePath));
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
            reader.close();

            JSONObject jsonObject = new JSONObject(stringBuilder.toString());
            JSONArray subjectsArray = jsonObject.getJSONArray("subjects");

            for (int i = 0; i < subjectsArray.length(); i++) {
                JSONObject subject = subjectsArray.getJSONObject(i);
                String subjectCode = subject.getString("subjectCode");
                String subjectType = subject.getString("subjectType");

                // 교양 유형에 해당하는 교과목을 맵에서 찾기
                if (subjectTypeMap.containsKey(subjectType)) {
                    Map<String, String> typeMap = subjectTypeMap.get(subjectType);
                    if (typeMap.containsKey(subjectCode)) {
                        String englishTypeName = typeMap.get(subjectCode);
                        System.out.println("Subject Code: " + subjectCode);
                        System.out.println("Subject Type: " + subjectType);
                        System.out.println("English Type Name: " + englishTypeName);
                    } else {
                        System.out.println("Subject Code: " + subjectCode);
                        System.out.println("Subject Type: " + subjectType);
                        System.out.println("English Type Name: Not Found");
                    }
                } else {
                    System.out.println("Subject Code: " + subjectCode);
                    System.out.println("Subject Type: " + subjectType);
                    System.out.println("English Type Name: Not Found");
                }
                System.out.println();
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
