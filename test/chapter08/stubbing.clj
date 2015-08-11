(ns chapter08.stubbing)

(def mock-calls (atom {}))

(defn stub-fn [the-function return-value]
  (swap! mock-calls assoc the-fuction [])
  (fn [& args]
    (swap! mock-calls update-in [the-function] conj args)
    return-value))

(defmacro stubbing [stub-forms & body]
  (let [stub-pairs (partition 2 stub-forms)
        returns (map last stub-pairs)
        real-fns (map first stub-pairs)
        stub-fns (map #(list 'stub-fn %1 %2) real-fns returns)]
    `(binding [~@(interleave real-fns stub-fns)]
       ~@body)))

(defn mock-fn [the-function]
  (stub-fn the-function nil))


