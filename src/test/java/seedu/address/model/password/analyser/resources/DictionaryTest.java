package seedu.address.model.password.analyser.resources;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import seedu.address.model.password.exceptions.DictionaryNotFoundException;

class DictionaryTest {

    @Test
    void build_invalidDictionaryName_throwsDictionaryException() {
        assertThrows(DictionaryNotFoundException.class, () -> Dictionary.build("dummy.txt"));
    }

    @Test
    void testBuild() {
        HashMap<String, Integer> test = new HashMap<>();
        test.put("password", 2);
        test.put("29tgl03", null);
        test.put("123123", 11);

        try {
            for (Map.Entry<String, Integer> entry : test.entrySet()) {
                String value = entry.getKey();
                Integer expected = entry.getValue();
                Integer computed = Dictionary.build("passwords.txt").getDictionary().get(value);
                assertEquals(expected, computed);
            }
        } catch (DictionaryNotFoundException e) {
            System.out.println("Should not happen");
            e.printStackTrace();
        }
    }

}
