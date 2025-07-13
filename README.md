# Introduction

quickdomain is a library where you can create dummy domains of your of domain object. This way you don't need to manually
create dummy objects for test cases or other usages.
It's still under construction and not for actual use yet.

# How to use
You will need an Api-Key from the supported LLM: 
- OpenAI   (<a href="#">get one here</a>)
- DeepSeek (<a href="#">get one here</a>)
- Gemini   (<a href="#">get one here</a>)

For example you have a domain called <code>User.class</code>
```
class User {
    String username;
    String email;
    
    public User() {}
    
    public User(String username, String email) {...}
    
    // class methods
}
```

Just create a <code>SimpleDomain</code> Object and you will get a List<User> with generated domain entities:
```
int numberOfEntities = 2;
SimpleDomainBuilder<User> builder = new SimpleDomainBuilder<>();
List<User> dummyDomain = builder.of(User.class, numberOfEntities)
                            .andProvider(GptProvider.OPEN_AI)
                            .andKey("yourApiKey").init();

sysout: [User{username='george44', email='someusername1@gmail.com'}, User{username='hellworld99', email='someemail2@gmail.com'}]
```