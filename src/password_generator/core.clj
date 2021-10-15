(ns password-generator.core)

(defn get-rand-char
  "Returns a random char from ASCII 33(!) to 126(~) inclusive"
  []
  ((comp str char +) (rand-int 94) 33))

(defn generate-password
  "Generates a random password given an input length"
  [len]
  (loop [pass ""
         i len]
    (if (> i 0)
      (recur (str pass (get-rand-char)) (dec i))
      pass)))

(defn console-logic
  "Allows the user to generate the password through the console"
  []
  (println (str "This password generator will prompt you for the length of a random password\n"
                "and generate a password containing letters, numbers, and special characters!\n"
                "----------------------------------------------------------------------------"))
  (loop [x true]
    (println "Enter the length for the password: ")
    (try
      ((comp println #(str "Password: " %) generate-password #(Integer/parseInt %) read-line))
      (catch Exception e (println "Invalid number.")))
    (println "Generate another password? ([Y]es/[N]o)")
    (let [ans (read-line)]
      (when (or (= (.toLowerCase ans) "yes") (= (.toLowerCase ans) "y")) (recur [x true])))))

(console-logic)