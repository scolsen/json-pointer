# Introduction 

*json-pointer* is a tiny library for parsing JSON pointers as they are defined in [RFC 6901](https://tools.ietf.org/html/rfc6901). JSON pointers are often used to cross-reference other components of a JSON document.

The library provides two primary functions for handling JSON pointers, `pointer->keys` and `pointer->strings`. Each function returns a sequence of the parts of the JSON pointer cast to a keyword or string. You can usually use this sequence to access a piece of JSON parsed using a library such as [cheshire] (https://github.com/dakrone/cheshire) by calling `get-in` on the parsed JSON object and the parsed JSON pointer.

# Example Usage 

The following example demonstrates a basic use of json-pointer to retreive a segment of a parsed JSON document.

```clojure
;;;;forthcoming
```
