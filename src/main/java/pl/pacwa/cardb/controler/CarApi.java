package pl.pacwa.cardb.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.pacwa.cardb.dao.CarDao;
import pl.pacwa.cardb.model.Car;
import pl.pacwa.cardb.model.Color;
import pl.pacwa.cardb.service.AddCar;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;

@Controller
public class CarApi {

    private final CarDao carDao;

    @Autowired
    public CarApi(CarDao carDao) {
        this.carDao = carDao;

    }

    @GetMapping("/index")
    public String getCars(Model model) {
        List<Car> allCars = carDao.findAllCars();
        model.addAttribute("cars", allCars);

        return "index";
    }

    @PostMapping("/add")
    public String addCar(@Validated AddCar addCar) {
        int isAdd = carDao.saveCar(addCar.getMark(), addCar.getModel(),
                addCar.getColor(), addCar.getProductionDate());
        if (isAdd == 1) {
            return "redirect:/index";
        }
        return "error";
    }

    @RequestMapping(value = "/modifyCar", method = {RequestMethod.PUT, RequestMethod.POST, RequestMethod.GET})
    public String modifyCar(@Validated Car modifiedCar) {
        int isModified = carDao.updateCar(modifiedCar);
        if (isModified == 1) {
            return "redirect:/index";
        }
        return "error";
    }

    @GetMapping("/delete")
    public String deleteCars(Long id) {
        int isRemoved = carDao.deleteCar(id);
        if (isRemoved == 1) {
            return "redirect:/index";
        }
        return "error";
    }

    @GetMapping("/carById")
    public ResponseEntity<Car> getCarById(@RequestParam long id, Model model) {
        Optional<Car> carOpt = carDao.getOneCar(id);
        return carOpt.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/carByDate")
    public String getCarsByDate(@RequestParam String from, @RequestParam String to, Model model){
        try {
            List<Car> allBetweenData = carDao.findCarsByDate(LocalDate.parse(from), LocalDate.parse(to));
            model.addAttribute("cars", allBetweenData);
            return "filterList";
        } catch (IllegalArgumentException | DateTimeParseException ex){
            System.out.println(ex.getMessage());
            return "error";
        }
    }

    @GetMapping("/carByColor")
    public String getCarsByColor(@RequestParam Color color, Model model){
        try {
            List<Car> allCarsColor = carDao.findCarsByColor(color);
            model.addAttribute("cars", allCarsColor);
            return "filterList";
        } catch (IllegalArgumentException ex){
            System.out.println(ex.getMessage());
            return "error";
        }
    }

    @GetMapping("/home")
    public String goToHomePage() {
        return "redirect:/index";
    }


}