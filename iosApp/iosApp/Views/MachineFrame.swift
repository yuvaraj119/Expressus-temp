import SwiftUI

struct MachineFrame<Content : View> : View {
    
    let content: () -> Content
        
    var body: some View {
        ThemeScope(theme: Themes.MachineFrame()) { theme in
            Panel(
                bottomOffset: 0,
                gradient:  LinearGradient(
                    gradient: Gradient(
                        colors: [theme.primary, theme.surface, theme.surface, theme.surface, theme.primary]),
                    startPoint: .leading,
                    endPoint: .trailing)
            ) { content() }
        }
    }
}

#Preview {
    MachineFrame {
        Text("Hello World")
            .foregroundColor(Color.white)
    }
}
