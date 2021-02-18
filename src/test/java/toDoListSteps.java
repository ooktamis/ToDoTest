import com.thoughtworks.gauge.Step;

public class toDoListSteps extends BaseTest {

    @Step("Anasayfa acildi")
    public void ToDoPage(){
        driver.get(url);
        System.out.println(convertTurkishChar("AnaSayfa Açıldı"));
    }

    @Step("<tagName> elementi var mi kontrol edilir")
    public void checkElementStep(String key) {
        try {
            findElement(key);
            System.out.println(key+" = Elementi var");
        } catch (Exception e) {
            System.out.println(key+" = Elementi yok ");
        }
    }

    @Step("<inputToDo> ya yeni item ekle <newTodo>")
    public void newToDo(String inputToDo, String newTodo) {
        sendkeysElement(inputToDo, newTodo);
        enterClick(inputToDo);
        System.out.println(convertTurkishChar("<newToDO> Fonksiyonu Çalıştı"));
    }

    @Step("<todoTagControl> da <inputToDo> var mi kontrol edilir")
    public void toDoControl(String todoControl, String inputToDo) {
        assertControls(todoControl,inputToDo);//Listede ekledigimiz tooodo var mi kontrolu
        System.out.println(inputToDo+" = Elemani var");
        System.out.println(convertTurkishChar("<toDoControl> Fonksiyonu Çalıştı"));
    }

    @Step("<item1> itemi <item2> altindami <itemControl>")
    public void itemLocation(String item1, String item2, String itemControl) {

        if (toDoIndex(item1,itemControl) > toDoIndex(item2,itemControl)) {
            System.out.println(convertTurkishChar("Altında"));
        } else {
            System.out.println(convertTurkishChar("Üzerinde"));
        }
        System.out.println(convertTurkishChar("<itemLocation> Fonksiyonu Çalıştı"));
    }

    @Step("<item> icin <radioButton> butonuna tikla <itemControl>")
    public void clickButton(String item, String radioButton, String itemControl) {

        listButtonClick( item, radioButton, itemControl);
        System.out.println(convertTurkishChar("<clickButton> Fonskiyonu Çalıştı"));
    }

    @Step("<item> itemi listede <toDoCompleted> mark edildimi kontrol edilir <itemControl>")
    public void markControl(String item, String toDoCompleted, String itemControl) {

        System.out.println(convertTurkishChar("<markControl> Fonksiyonuna Girildi"));

        if (markControlBase(item, toDoCompleted, itemControl) == true) {
            System.out.println(convertTurkishChar("Mark Edildi"));
        } else
            System.out.println(convertTurkishChar("Mark Edilmedi"));

        System.out.println(convertTurkishChar("Mark Kontrol Edildi, <markControl> Fonsiyonu Çalıştı"));
        //tolistesini li taggine tikladigimizda to do completed oldugunu gormek icin kullandik aldik
    }

    @Step("<saniye> saniye bekle")
    public void waitElement(int key) throws InterruptedException {
        Thread.sleep(key*1000);
        System.out.println(key+" saniye beklendi");
    }
}