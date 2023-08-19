		let a;
		let b;
		let i;
		i = 0;
		a = 1;
		b = 10;
		if (a>b && a<b || a>b) {
			console.log(a);
		} else{
			console.log(b);
		}
		if (a==1 || a>b) {
			console.log(a);
		} else{
			console.log(b);
		}
		do {
			console.log(i);
			i = i+1;
		} while (i<3 || i>4);

		do {
			if (a>b && a<b || a>b) {
			console.log(a);
		} else{
			console.log(b);
		}			i = i+1;
		} while (i<3 || i>4);

		i = 0;
		do {
			console.log(i);
			i = i+1;
		} while (i<3+3 && i>3);

