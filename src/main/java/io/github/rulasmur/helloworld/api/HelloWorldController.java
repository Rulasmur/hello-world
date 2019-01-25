package io.github.rulasmur.helloworld.api;

import io.github.rulasmur.helloworld.api.models.APIMessage;
import io.github.rulasmur.helloworld.exceptions.APIExistsException;
import io.github.rulasmur.helloworld.exceptions.APIInvalidParameterException;
import io.github.rulasmur.helloworld.exceptions.APINotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HelloWorldController {

    private static Map<String, String> mappings = new HashMap<>();

    @GetMapping("/hello-world")
    public APIMessage get(@RequestParam String key)
    {
        if(key == null)
        {
            throw new APIInvalidParameterException();
        }

        if(!mappings.containsKey(key))
        {
            throw new APINotFoundException();
        }

        return  new APIMessage("Hello " + mappings.get(key));
    }
    @PutMapping("/hello-world")
    public void update(@RequestParam String key, @RequestParam String value)
    {
        if(key == null || value == null)
        {
            throw new APIInvalidParameterException();
        }

        if(!mappings.containsKey(key))
        {
            throw new APINotFoundException();
        }

        mappings.put(key, value);
    }

    @PostMapping("/hello-world")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestParam String key, @RequestParam String value)
    {
        if(key == null || value == null)
        {
            throw new APIInvalidParameterException();
        }

        if(mappings.containsKey(key))
        {
            throw new APIExistsException();
        }

        mappings.put(key, value);
    }

    @DeleteMapping("/hello-world")
    public void delete(@RequestParam String key)
    {
        if(key == null)
        {
            throw new APIInvalidParameterException();
        }

        if(!mappings.containsKey(key))
        {
            throw new APINotFoundException();
        }

        mappings.remove(key);
    }



}
