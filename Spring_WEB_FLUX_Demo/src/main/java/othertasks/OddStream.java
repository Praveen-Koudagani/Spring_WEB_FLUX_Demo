package othertasks;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class OddStream {
public static void main(String[] args){
	List<Integer> numbers=new ArrayList<>();
	for(int i=0;i<50;i++){
		numbers.add(i);
	}
    List<Integer> oddNumbers=numbers.stream().filter(i->i%2==1).collect(Collectors.toList());
    oddNumbers.forEach(i->log.info(i.toString()));
}
}
